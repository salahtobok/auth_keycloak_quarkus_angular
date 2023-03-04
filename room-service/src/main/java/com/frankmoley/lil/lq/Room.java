package com.frankmoley.lil.lq;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Room", schema = DbSchema.APPLICATION_SCHEMA)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "bed_info")
    private String bedInfo;

    public Room(String name, String roomNumber, String bedInfo) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.bedInfo = bedInfo;
    }


    public Room() {
        super();
    }


    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                '}';
    }
}
