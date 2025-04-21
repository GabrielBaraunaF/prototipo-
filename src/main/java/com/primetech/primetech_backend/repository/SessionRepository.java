package com.primetech.primetech_backend.repository;

import com.primetech.primetech_backend.entity.Room;
import com.primetech.primetech_backend.entity.Session;
import com.primetech.primetech_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository <Session,Integer> {

    @Query(value = "select * from Session where room_id =:roomId and timeslot_id =:timeslotId", nativeQuery = true)
    Session findEspefic(Integer roomId, Integer timeslotId);

    List<Session> findByroomIdAndDate(Room room, Date date);

    List<Session> findByuserId(User user);
}

