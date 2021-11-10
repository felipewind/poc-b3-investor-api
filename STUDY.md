# Microprofile RESTClient specification

https://download.eclipse.org/microprofile/microprofile-rest-client-2.0/microprofile-rest-client-spec-2.0.html


# Difference between trustStore and keyStore in Java

https://www.geeksforgeeks.org/difference-between-truststore-and-keystore-in-java/

> TrustStore is used to store certificates from Certified Authorities (CA) that verify the certificate presented by the server in an SSL connection. While Keystore is used to store private key and identity certificates that a specific program should present to both parties (server or client) for verification. This concludes that they are opposite of each other. In a layman’s language, we can directly conclude up that in a certification trustStore holds identification certificates that identify others while keyStore holds the identification certificates that hold us. 


# KeyStore

## Generating JKS file to the key store from cer and key

https://stackoverflow.com/questions/38250271/creating-a-jks-from-a-crt-and-key-file-is-that-possible

```
keytool -importkeystore -srckeystore 00000191237523.p12 -srcstoretype pkcs12 -srcstorepass KKSAWH -destkeystore b3_api.jks -deststoretype jks -deststorepass KKSAWH
```

# TrustStore

## Generating JKS file 

https://www.google.com/search?channel=fs&client=ubuntu&q=how+to+generate+jks+from+crt

https://stackoverflow.com/questions/11952274/how-can-i-create-keystore-from-an-existing-certificate-abc-crt-and-abc-key-fil

https://docs.oracle.com/javase/tutorial/security/toolsign/rstep2.html

```
$ keytool -import -alias b3-cert-api -file b3-cert-api.crt -keystore b3_cert_api_ca.jks -storepass B3APICA -noprompt
```


# Import the B3 ssl certificate into your Java platform

## Create the root.crt file

```
$ openssl s_client -showcerts -connect apib3i-cert.b3.com.br:2443
```

Copy the content between `-----BEGIN CERTIFICATE-----` and `-----END CERTIFICATE-----` and save it into a file called `b3-cert-api.crt`.

Or just use [this crt file](./certificates/b3-cert-api.crt) already created.

## Import the file to the trusted root certificate of the Java platform

Find the cacerts file. It could be like `/etc/ssl/certs/java/cacerts`.

Execute the keytool command to import it:
```
sudo keytool -importcert -keystore /etc/ssl/certs/java/cacerts -storepass changeit -file /your-path/b3-cert-api.crt -alias "b3-cert-api-root"
```

## Delete the CA certificate from cacerts

In case you need to delete:
```
keytool -delete -noprompt -alias "b3-cert-api-root" -keystore /etc/ssl/certs/java/cacerts -storepass changeit
```

## Website with complete instructions to import a ssl certificate
https://jfrog.com/knowledge-base/how-to-resolve-unable-to-find-valid-certification-path-to-requested-target-error/    
