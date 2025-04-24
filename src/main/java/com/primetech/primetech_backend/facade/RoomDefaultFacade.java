package com.primetech.primetech_backend.facade;

import com.primetech.primetech_backend.dto.SessionDTO;
import com.primetech.primetech_backend.entity.Room;
import com.primetech.primetech_backend.entity.Session;
import com.primetech.primetech_backend.entity.User;
import com.primetech.primetech_backend.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomDefaultFacade implements RoomFacade {

    @Autowired
    private RoomService roomService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void createRoom(Room room) {
        roomService.save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        Room roomExits = roomService.findById(room.getId());
        roomExits.setIsAvailable(room.getIsAvailable());
        roomExits.setMaintenanceReason(room.getMaintenanceReason());
        return roomService.update(roomExits);
    }

    @Override
    public List<Room> roomList() {
        return roomService.listarSalas();
    }


    @Transactional
    @Override
    public void createSession(SessionDTO sessionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findUserByEmail(email);

        Room room = roomService.findById(sessionDTO.getRoomId());
        sessionService.isRoomAvailable(room, sessionDTO.getDate(), sessionDTO.getStartTime(), sessionDTO.getEndTime());
        Session session = sessionService.save(sessionDTO, user);


    }


    @Override
    public Session findSessionById(Integer id) {
        return sessionService.find(id);
    }

    @Override
    public Boolean isRoomAvailable(SessionDTO sessionDTO) {
        Room room = roomService.findById(sessionDTO.getRoomId());
        return sessionService.isRoomAvailable(room, sessionDTO.getDate(), sessionDTO.getStartTime(), sessionDTO.getEndTime());
    }

    @Override
    public List<Session> sessionList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findUserByEmail(email);
        return sessionService.sessionList(user);
    }

    @Override
    public Session updateSession(SessionDTO sessionDTO) {
        System.out.println(sessionDTO.getConfirmed());
        Session session = sessionService.find(sessionDTO.getRoomId());
        session.setConfirmed(sessionDTO.getConfirmed());
        return sessionService.update(session);
    }
}
