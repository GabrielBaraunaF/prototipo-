package com.primetech.primetech_backend.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AvailabityDTO {

    private Integer timeslotId;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean isAvailable;

}
