package com.admin.business.rest_resource;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;
import static org.keycloak.OAuth2Constants.PASSWORD;

@Path("/api/admin")
public class RolesResource {

//    @Inject
//    Keycloak keycloak;
    Keycloak keycloak1;

    Keycloak adminCli ;

    @PostConstruct
    public void initKeycloak() {
         adminCli = KeycloakBuilder.builder()
                 .serverUrl("http://localhost:8543")
                 .realm("quarkus")
                 .clientId("realm-management")
                 .clientSecret("mo0yMEGOSjvg2hlYvMvUYokeXgh1Cbip")
                 .username("salahtobok")
                 .password("root")
                 .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
/*        keycloak1 = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8543")
                .realm("quarkus")
                .clientId("backend-service")
                .clientSecret("76e5X5DjYZWUX0a4TsP7njBYVVfrlT6p")
                .username("salahtobok")
                .password("root")
                .grantType(PASSWORD)
                .build();*/


        keycloak1 = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8543")
                .realm("quarkus")
                .clientId("backend-service")
                .clientSecret("76e5X5DjYZWUX0a4TsP7njBYVVfrlT6p")
                .username("salahtobok")
                .password("root")
                .grantType(CLIENT_CREDENTIALS)
                .build();
//        quarkus.keycloak.admin-client=true
//        quarkus.keycloak.admin-client.server-url=http://localhost:8081
//        quarkus.keycloak.admin-client.realm=quarkus
//        quarkus.keycloak.admin-client.client-id=quarkus-client
//        quarkus.keycloak.admin-client.client-secret=secret
//        quarkus.keycloak.admin-client.username= # remove default username
//        quarkus.keycloak.admin-client.password= # remove default password
//        quarkus.keycloak.admin-client.grant-type=CLIENT_CREDENTIALS
        /*keycloak1 = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8543")
                .realm("master")
                .clientId("admin-cli")
                .username("salahtobok")
                .password("root")
                .grantType(PASSWORD)
                .build();*/
    }


    @GET
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        try {
            System.out.println("keycloak1.realm(\"quarkus\").roles().list() = " + keycloak1.tokenManager().grantToken());
        }catch (Exception exception){
            System.out.println("exception.getMessage() keycloak1 = " + exception.getMessage());
        }
        return new ArrayList<>();
    }

}