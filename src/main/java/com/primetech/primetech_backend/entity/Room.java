package com.primetech.primetech_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String capacity;

    private String type;

    private String name;

    private String code;

    private String description;
}
