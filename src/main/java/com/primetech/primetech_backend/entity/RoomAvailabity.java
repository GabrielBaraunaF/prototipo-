package com.primetech.primetech_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "roomavailabity")
@Data
public class RoomAvailabity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;
    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room roomId;

    @ManyToOne
    @JoinColumn(name = "timeslot_id",referencedColumnName = "id")
    private TimeSlot timeslotId;


}
