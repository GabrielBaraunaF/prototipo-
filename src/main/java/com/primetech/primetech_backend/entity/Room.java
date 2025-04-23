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

    private String name;

    private String code;

    private String description;

    @Column(name = "maintenance_reason")
    private String maintenanceReason;

    @Column(name = "is_Available")
    private Boolean isAvailable;

}
