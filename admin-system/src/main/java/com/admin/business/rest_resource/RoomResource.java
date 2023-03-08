package com.admin.business.rest_resource;

import com.admin.Room;
import com.admin.business.registered_rest_client.RoomServiceRest;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.keycloak.admin.client.Keycloak;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/api/rooms")
@Authenticated
public class RoomResource {

//    @Inject
//    @RestClient
//    RoomServiceRest roomService;

    @Inject
    Keycloak keycloak;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("FETCH_ROOMS")
    public List<Room> getAllRooms(){
//        keycloak.tokenManager().getAccessToken()

        List<Room> rooms = new ArrayList<>();
        for(int i = 1;i < 20; i++){
            rooms.add(new Room(UUID.randomUUID().toString(),UUID.randomUUID().toString(),UUID.randomUUID().toString()));
        }
//        rooms = roomService.getAllRooms();
        return rooms;
    }
}
