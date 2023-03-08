//package com.admin.business.rest_resource;
//
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.representations.idm.RoleRepresentation;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import java.util.List;
//
//import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;
//import static org.keycloak.OAuth2Constants.PASSWORD;
//
//@Path("/tokenManager")
//public class FetchAccessTokenResource {
//
//    @Inject
//    Keycloak keycloak;
//    Keycloak keycloakPasswordGrantType;
//    Keycloak keycloakClientCredentialsGrantType;
//
//    @PostConstruct
//    public void initKeycloak() {
//        keycloakPasswordGrantType = KeycloakBuilder.builder()
//                .serverUrl("http://localhost:8543")
//                .realm("quarkus")
//                .clientId("backend-service")
//                .clientSecret("secret")
//                .username("salahtobok")
//                .password("root")
//                .grantType(PASSWORD)
//                .build();
//
//        // To order to enable this type of access you should set "Service Accounts Enabled = Enabled" end set Access Type="CREDENTIAL"
//        // In client Setting
//        keycloakClientCredentialsGrantType = KeycloakBuilder.builder()
//                .serverUrl("http://localhost:8543")
//                .realm("quarkus")
//                .clientId("backend-service")
//                .clientSecret("secret")
//                .grantType(CLIENT_CREDENTIALS)
//                .build();
//        }
//
//
//    @GET
//    @Path("/accessToken")
//    public List<RoleRepresentation> getRoles() {
//        try {
//            System.out.println("Access token to call the (Master) 'Realm' Admin REST API which requires authorization = " + keycloak.tokenManager().grantToken().getToken());
//        }catch (Exception exception){
//            System.out.println("exception.getMessage() = " + exception.getMessage());
//        }
//
//
//        try {
//            System.out.println("Access token to call the (salahtobok) 'User' Admin REST API which requires authorization  = " + keycloakPasswordGrantType.tokenManager().grantToken().getToken());
//        }catch (Exception exception){
//            System.out.println("exception.getMessage() = " + exception.getMessage());
//        }
//
//        try {
//            System.out.println("Access token to call the (backend-service) 'Client' Admin REST API which requires authorization = " + keycloakClientCredentialsGrantType.tokenManager().grantToken().getToken());
//        }catch (Exception exception){
//            System.out.println("exception.getMessage() = " + exception.getMessage());
//        }
//
//        return keycloak.realm("quarkus").roles().list();
//    }
//
//}