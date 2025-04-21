package com.primetech.primetech_backend.repository;


import com.primetech.primetech_backend.entity.RoomAvailabity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAvailabityRepository extends JpaRepository<RoomAvailabity,Integer> {

    @Query(value = "select * from roomavailabity where room_id =:id and timeslot_id =:timeslotId",nativeQuery = true)
    RoomAvailabity find(Integer id,Integer timeslotId);


    @Query(value = "select * from roomavailabity where room_id =:id",nativeQuery = true)
    List<RoomAvailabity> listarHorarios(Integer id);

}
