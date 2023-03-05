mvn io.quarkus.platform:quarkus-maven-plugin:2.16.4.Final:create \
-DprojectGroupId=org.acme \
-DprojectArtifactId=security-keycloak-admin-client \
-Dextensions='keycloak-admin-client-reactive,resteasy-reactive-jackson' \
-DnoCode
cd security-keycloak-admin-client