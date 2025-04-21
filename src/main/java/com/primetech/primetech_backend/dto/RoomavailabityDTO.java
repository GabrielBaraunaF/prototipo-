package com.primetech.primetech_backend.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoomavailabityDTO {

    private Date date;

    private Integer room_id;

    private List<AvailabityDTO> availabity;
}
