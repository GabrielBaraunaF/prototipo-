package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.dto.SessionDTO;
import com.primetech.primetech_backend.entity.Room;
import com.primetech.primetech_backend.entity.Session;
import com.primetech.primetech_backend.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public interface SessionService {

    Session save(SessionDTO sessionDTO, User user);

    Session find(Integer id);

    Session findEspecif(Integer roomID, Integer timeslotID);

    List<Session> sessionList(User user);

    boolean isRoomAvailable(Room room, Date date, LocalTime start, LocalTime end);
}
