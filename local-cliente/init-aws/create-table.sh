#!/bin/bash
awslocal dynamodb create-table \
   --table-name tb_cliente \
   --attribute-definitions AttributeName=cpf,AttributeType=S \
   --key-schema AttributeName=cpf,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5