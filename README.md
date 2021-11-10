# poc-b3-investor-api
POC of APIs from B3 - Investor - New Logged Area/Nova Área Logada

[B3 API Documentation](https://developers.b3.com.br/apis-br)
- At the moment, this documentation is only available on Portuguese.

The objective of this POC is to execute the healthcheck endpoint of B3 investor certification environment.

In this POC you will find example to:
- Consume one endpoint with two-way SSL connections;
- Generate Trust Store in a Java Key Store (JKS) format from one HTTPS endpoint;
- Generate Key Store in a JKS format from PFX (P12) file;
- Consume HTTP with Microprofile REST Client using:
  - Authorization Bearer in the Header;
  - Trust Store parameters;
  - Key Store parameters.

Fast instructions:
- Obtain the Access Package;
- Create the key store file;
- Write the password of the key store in the application.properties;
- Run the project.

```
./mvnw quarkus:dev
```

Access the `/TOKEN` endpoint passing your client_id and secret to receive the token.

Execute the `/api/healthcheck/{token}` with the token and recive the success response:
```json
{
  "status": "Sucesso",
  "mensagem": "Autenticação e autorização do usuário 42451170000132 validadas com sucesso."
}
```


# Instructions to execute the B3 certification APIs

## Obtain the Access Package

According [B3 documentation](https://developers.b3.com.br/index.php?option=com_apiportal&view=documentation&id=pacote-de-acesso), first, you have to execute this endpoint:

POST https://apib3i-cert.b3.com.br/api/acesso/autosservico

Passing this body:
```json
{   
    "nome": "Company name",
    "documento": "42451170000132",
    "email": "email@emailcom.br"
} 
```

"documento" is your company CNPJ (Brazilian code for companies).

You will receive one access package in the email you informed.

## Generate your Key Store file - b3_api.jks

Execute the following command to generate the key store file (b3_api.jks):

```
keytool -importkeystore -srckeystore 42451170000132.p12 -srcstoretype pkcs12 -srcstorepass ABCDEF -destkeystore b3_api.jks -deststoretype jks -deststorepass KKSAWH
```

These parameters were received in your access package:
- 42451170000132.p12 - your P12 (PFX);
- ABCDEF - your password;

Copy the b3_api.jks file to your src/main/resources folder.

## Write the trustStorePassword on the application.properties

In the src/main/resources/application.properties inform the `org.acme.B3ApiService/mp-rest/keyStorePassword` with the password you received in the access package.


## Obtain the Certificate Authority file (CA) - Optional

This is optional, because the b3_cert_api_ca is already present in the resources of this project.

```
$ openssl s_client -showcerts -connect apib3i-cert.b3.com.br:2443
```

Copy the content between `-----BEGIN CERTIFICATE-----` and `-----END CERTIFICATE-----` and save it into a file called `b3-cert-api.crt`.

Or just use [this crt file](./certificates/b3-cert-api.crt) already created.


## Generate your Trust Store file - b3_cert_api_ca.jks - Optional

This is optional, because the b3_cert_api_ca is already present in the resources of this project.

```
$ keytool -import -alias b3-cert-api -file b3-cert-api.crt -keystore b3_cert_api_ca.jks -storepass B3APICA -noprompt
```

Copy the b3_cert_api_ca.jks file to your src/main/resources folder.






# Quarkus original - poc-b3-investor-api Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/poc-b3-investor-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Related Guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
