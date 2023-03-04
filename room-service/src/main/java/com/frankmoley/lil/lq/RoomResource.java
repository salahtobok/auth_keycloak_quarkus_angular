package com.frankmoley.lil.lq;

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
    RoomService roomService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        rooms = roomService.getAllRooms();
        return rooms;
    }
}
