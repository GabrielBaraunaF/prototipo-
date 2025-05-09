package com.primetech.primetech_backend.facade;

import com.primetech.primetech_backend.dto.RoomavailabityDTO;
import com.primetech.primetech_backend.dto.SessionDTO;
import com.primetech.primetech_backend.entity.Room;
import com.primetech.primetech_backend.entity.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomFacade {

    void createRoom(Room room);

    Room updateRoom(Room room);

    List<Room> roomList();

    List<Session> sessionListAll();

    List<Room> roomListAvailable();

    void createSession(SessionDTO sessionDTO);

    Session findSessionById(Integer id);

    Boolean isRoomAvailable(SessionDTO sessionDTO);

    List<Session> sessionList();

    Session updateSession(SessionDTO sessionDTO);
}
