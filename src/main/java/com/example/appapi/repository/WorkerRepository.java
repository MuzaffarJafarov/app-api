package com.example.appapi.repository;

import com.example.appapi.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    Boolean existsByPhoneNumber(String phoneNumber);
//    @Query("select (count(w) > 0) from Worker w where w.phoneNumber = ?1 and w.id <> ?2")
    Boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
