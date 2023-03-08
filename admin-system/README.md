# authentification_quarkus_angular_keycloak Project

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

You can then execute your native executable with: `./target/ads-system-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.


<hr/>
<br/>

## Steps to run the application : 
<h3>01 - Create postgres database "quarkus_admin"</h3>


## Manually fetch access token from keycloak :

<h3>01 - Get Token with PowerShell</h3>
Command begin -- :
<br>
$headers = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"
<br>
$headers.Add("Content-Type", "application/x-www-form-urlencoded")
<br>
$body = "username=salahtobok&password=root&grant_type=password&client_id=backend-service&client_secret=76e5X5DjYZWUX0a4TsP7njBYVVfrlT6p&scope=openid"
<br>
$token_response = Invoke-RestMethod 'http://localhost:8543/realms/quarkus/protocol/openid-connect/token' -Method 'POST' -Headers $headers -Body $body
<br>
$token_response | ConvertTo-Json
<br>
Command end --
<br>
<h3>02 - Retrieve my using Keycloak ( Auth http://localhost:8080/api/users/me )</h3>
<br>
$headers = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"
<br>
$headers.Add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaXzg2UlhKV0ptMmlkVW5rMUg4WkVXQU1zNm4tZ3c5ZTFoVzk2X25OOVFrIn0.eyJleHAiOjE2NzEzNjA3MzgsImlhdCI6MTY3MTM2MDQzOCwianRpIjoiZGNhOGJiZDktNDM2OS00Yjc3LTlkMTItZjc1YWIyMTczMWM3IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4NTQzL3JlYWxtcy9xdWFya3VzIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjM4NzcwY2QyLTU5MTAtNDExOS05OGI2LTI3YTYxYjI3ZjIxNSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImJhY2tlbmQtc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiI1MDVkMTY1Ny1kNzEyLTRkYzItYTFiYi0zNzRkMzJjMmVkYzIiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXF1YXJrdXMiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiNTA1ZDE2NTctZDcxMi00ZGMyLWExYmItMzc0ZDMyYzJlZGMyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJUT0JPSyBTQUxBSCBFRERJTkUiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzYWxhaHRvYm9rIiwiZ2l2ZW5fbmFtZSI6IlRPQk9LIiwiZmFtaWx5X25hbWUiOiJTQUxBSCBFRERJTkUiLCJlbWFpbCI6InNhbGFodG9ib2sxQGdtYWlsLmNvbSJ9.MznWrzZB1E7NiOl0nxaWVQghv0TYbS4K572YTpUl_QFxFI2-8adTlzDcSKjKX0JlejmipORNXaRezb-DqieZYUSvd0y16xbVsOTiwZL7znwz3yCL9wAfmO3p0MhjKRXE41xZ1MX5haGRZifpfwiRDZPkbYl90u0jUHQWuCeieI10U9uoJK9xopSfur8Br48PTS7Eimo943cvS-dKOOtielLHVMEM-AT-yXwENEIqtSDVxckiw1S3FXvFbV4_k3Cqz60j-hCl9VK-yC0YmItC6wlO5RpPBc0PRrDO660h5sTUK6b-wV4u9OCpo-HakSQv7ll09LKXeos3uNBqcBUEfQ")
<br>
$response = Invoke-RestMethod 'http://localhost:8080/api/users/me' -Method 'GET' -Headers $headers
<br>
$response | ConvertTo-Json
<br>

<h3>Or using shorthand command</h3>
<br>
$headers = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"
<br>
$headers.Add("Authorization", "Bearer "+$token_response.access_token)
<br>
$response = Invoke-RestMethod 'http://localhost:8080/api/users/me' -Method 'GET' -Headers $headers
<br>
$response | ConvertTo-Json
<br>

## Useful commands :
<h4>01 - How do I kill the process currently using a port on localhost in Windows?</h3>
01 - netstat -ano | findstr :<PORT><br>
02 - taskkill /PID <PID> /F


## Note : 

<p>Adding this dependency</p>  

    <dependency>
      <groupId>javax.faces</groupId>
      <artifactId>javax.faces-api</artifactId>
      <version>2.3</version>
      <scope>provided</scope>
    </dependency>

or

    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-resteasy-reactive-jsonb</artifactId>
    </dependency>

<p>will throw java.lang.NullPointerException: Cannot invoke "String.startsWith(String)" because "authHeader" is null</p>

## Guide For "USING OPENID CONNECT (OIDC) AND KEYCLOAK TO CENTRALIZE AUTHORIZATION"
<p>https://quarkus.io/guides/security-keycloak-authorization</p>

## Guide For "USING KEYCLOAK ADMIN CLIENT"
<p>https://quarkus.io/guides/security-keycloak-admin-client</p>

## Use @RoleAllowed To Scan the roles which caming from the jwt
<p>Add these two extension : ./mvnw quarkus:add-extension -Dextensions='smallrye-jwt,smallrye-jwt-build'</p>
<p>Read this article : https://quarkus.io/guides/security-jwt</p>

## Notes :
<p>01 - Don't use comment in application.properties files</p>
<p>02 - Annotating the resource with @Authenticated without enabling "Authorization Enabled" in our client will throw 500 (Internal Server Error) in browser console</p>