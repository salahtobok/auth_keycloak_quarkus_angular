package com.frankmoley.lil.lq;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoomService {
    private static final Logger LOG = Logger.getLogger(RoomService.class);


    @Inject
    EntityManager entityManager;

    public List<Room> getAllRooms(){

        LOG.debug("The application start");

        List<Room> rooms = new ArrayList<>();

        rooms = entityManager.createQuery("select r from Room r",Room.class).getResultList();
        rooms.forEach(System.out::println);

        LOG.debug("The application end");

        return rooms;
    }
}
