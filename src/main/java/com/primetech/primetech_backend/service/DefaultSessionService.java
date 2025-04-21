package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.dto.SessionDTO;
import com.primetech.primetech_backend.entity.Room;
import com.primetech.primetech_backend.entity.Session;
import com.primetech.primetech_backend.entity.User;
import com.primetech.primetech_backend.exception.ApplicationException;
import com.primetech.primetech_backend.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
public class DefaultSessionService implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Override
    public Session save(SessionDTO sessionDTO,User user) {
        Session session = convertToSession(sessionDTO);
        session.setUserId(user);

        return repository.save(session);
    }

    @Override
    public Session find(Integer id){
        return  repository.findById(id).get();
    }

    @Override
    public Session findEspecif(Integer roomID, Integer timeslotID){
        return repository.findEspefic(roomID, timeslotID);
    }


    private Session convertToSession(SessionDTO sessionDTO){
        Session session = new Session();
        session.setDate(sessionDTO.getDate());

        Room room = new Room();
        room.setId(sessionDTO.getRoomId());

        session.setRoomId(room);
        session.setDate(sessionDTO.getDate());

        session.setStartTime(sessionDTO.getStartTime());
        session.setEndTime(sessionDTO.getEndTime());

        return session;

    }

    @Override
    public boolean isRoomAvailable(Room room, Date date, LocalTime start, LocalTime end) {
        List<Session> sessions = repository.findByroomIdAndDate(room, date);

        for (Session s : sessions) {
            if (start.isBefore(s.getEndTime()) && end.isAfter(s.getStartTime())) {
                throw new ApplicationException("horario ja reservado");
            }
        }

        return true;
    }
}
