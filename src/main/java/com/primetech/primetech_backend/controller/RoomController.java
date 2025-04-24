package com.primetech.primetech_backend.controller;


import com.primetech.primetech_backend.dto.RoomavailabityDTO;
import com.primetech.primetech_backend.dto.SessionDTO;
import com.primetech.primetech_backend.entity.Room;
import com.primetech.primetech_backend.entity.Session;
import com.primetech.primetech_backend.facade.RoomFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomFacade roomFacade;

    @PostMapping("/save")
    public ResponseEntity creteRoom(@RequestBody Room room){
        roomFacade.createRoom(room);
        return ResponseEntity.ok("funcionou");
    }

    @PostMapping("/update")
    public Room updateRoom(@RequestBody Room room){
        return roomFacade.updateRoom(room);
    }


    @Operation(summary = "listar todas as salas",
            description = "listar todas as salas"
    )
    @GetMapping("/list")
    public List<Room> findAll() {
        return roomFacade.roomList();
    }

    @GetMapping("/list/available")
    public List<Room> findAllAvaliable() {
        return roomFacade.roomListAvailable();
    }


    @Operation(summary = "Criar nova Sessao(reservar sala)",
            description = "Criar nova Sessao"
    )
    @PostMapping("/session")
    public ResponseEntity saveSession(@RequestBody SessionDTO sessionDTO){
        roomFacade.createSession(sessionDTO);
        return ResponseEntity.ok("funcionou");
    }

    @PostMapping("/session/update")
    public Session updateSession(@RequestBody SessionDTO sessionDTO){
        return roomFacade.updateSession(sessionDTO);
    }

    @PostMapping("/available")
    public Boolean isRoomAvailable(@RequestBody SessionDTO sessionDTO){
        return roomFacade.isRoomAvailable(sessionDTO);
    }

    @Operation(summary = "se necessario ",
            description = "procura session especifica"
    )
    @GetMapping("/session/{id}")
    public Session findSession(@PathVariable Integer id){
        return roomFacade.findSessionById(id);
    }

    @GetMapping("/session/list")
    public List<Session> sessionList(){
        return roomFacade.sessionList();
    }

    private boolean hasRole(Authentication authentication, String roleName) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(roleName));
    }


}
