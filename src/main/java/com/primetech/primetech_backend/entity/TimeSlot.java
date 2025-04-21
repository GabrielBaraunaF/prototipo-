package com.primetech.primetech_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;


@Entity
@Table(name = "timeslot")
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalTime start_time;
    private LocalTime end_time;


}
