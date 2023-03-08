package com.admin.business.rest_resource;

import com.admin.Room;
import com.admin.business.registered_rest_client.RoomServiceRest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.keycloak.admin.client.Keycloak;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/rooms")
public class RoomResource {

    @Inject
    @RestClient
    RoomServiceRest roomService;

    @Inject
    Keycloak keycloak;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
//        keycloak.tokenManager().getAccessToken()
        List<Room> rooms = new ArrayList<>();
        rooms = roomService.getAllRooms();
        return rooms;
    }
}
