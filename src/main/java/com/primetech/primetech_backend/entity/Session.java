package com.primetech.primetech_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room roomId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East" )
    private Date date;

    @Column(name = "start_Time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private LocalTime startTime;

    @Column(name = "end_Time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private LocalTime endTime;

    private Boolean confirmed;
}
