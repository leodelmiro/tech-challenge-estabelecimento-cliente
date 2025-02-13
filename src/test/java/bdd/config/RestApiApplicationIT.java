package bdd.config;

import com.leodelmiro.cliente.ClienteApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=8090"
})
@CucumberContextConfiguration
@ContextConfiguration(classes = {ClienteApplication.class})
@ActiveProfiles("test")
@Testcontainers
public class RestApiApplicationIT {

    @Container
    private static final LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse("localstack/localstack"))
            .withExposedPorts(4566)
            .withServices(LocalStackContainer.Service.DYNAMODB)
            .withCopyToContainer(MountableFile.forClasspathResource("init-aws", 0744), "/etc/localstack/init/ready.d")
            .waitingFor(Wait.forLogMessage(".*Ready\\.\n", 1));;

    @LocalServerPort
    private int port;

    static {
        localStack.start();
    }

    @BeforeEach
    public void setupBefore() throws Exception {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterEach
    public void setupAfter() {
        localStack.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("amazon.dynamodb.endpoint", localStack::getEndpoint);
        registry.add("amazon.aws.accesskey", localStack::getAccessKey);
        registry.add("amazon.aws.secretkey", localStack::getSecretKey);
        registry.add("aws.region", localStack::getRegion);
    }


    @Test
    void deveIniciarAplicacaoCorretamente() {
        given()
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("status", equalTo("UP"));
    }
}