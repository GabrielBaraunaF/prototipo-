package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.dto.AvailabityDTO;
import com.primetech.primetech_backend.dto.RoomavailabityDTO;
import com.primetech.primetech_backend.entity.RoomAvailabity;
import com.primetech.primetech_backend.exception.ApplicationException;
import com.primetech.primetech_backend.repository.RoomAvailabityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultRoomAvailabity implements RoomAvailabityService {
    @Autowired
    private RoomAvailabityRepository roomAvailabityRepository;

    @Override
    public void save(Integer roomId, Integer timeslotId) {
        RoomAvailabity roomAvailabity = roomAvailabityRepository.find(roomId, timeslotId);
        if (!roomAvailabity.getIsAvailable()){
            throw new ApplicationException("Ja existe um agendamento para essa sala");
        }

        System.out.println(roomAvailabity);
        roomAvailabity.setIsAvailable(false);
        roomAvailabityRepository.save(roomAvailabity);
    }


    @Override
    public RoomavailabityDTO listarHorarios(Integer idSala) {

        List<RoomAvailabity> list = roomAvailabityRepository.listarHorarios(idSala);

        RoomavailabityDTO roomavailabityDTO = toRoomavailabityDTO(idSala, list);

        return roomavailabityDTO;

    }


    private RoomavailabityDTO toRoomavailabityDTO(Integer idSala, List<RoomAvailabity> list) {
        List<AvailabityDTO> availabityDTOs = list.stream()
                .map(roomAvailability -> {
                    AvailabityDTO dto = new AvailabityDTO();
                    dto.setTimeslotId(roomAvailability.getTimeslotId().getId());
                    dto.setStartTime(roomAvailability.getTimeslotId().getStart_time());
                    dto.setEndTime(roomAvailability.getTimeslotId().getEnd_time());
                    dto.setIsAvailable(roomAvailability.getIsAvailable());
                    return dto;
                })
                .collect(Collectors.toList());

        RoomavailabityDTO roomavailabityDTO = new RoomavailabityDTO();
        roomavailabityDTO.setDate(Date.valueOf(LocalDate.now()));
        roomavailabityDTO.setRoom_id(idSala);
        roomavailabityDTO.setAvailabity(availabityDTOs);
        return roomavailabityDTO;
    }
}
