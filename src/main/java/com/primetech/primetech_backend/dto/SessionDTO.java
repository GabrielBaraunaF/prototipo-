package com.primetech.primetech_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
public class SessionDTO {


    private Integer roomId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East" )
    private Date date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private LocalTime endTime;

    private Boolean confirmed;

}
