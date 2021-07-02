package com.example.appapi.repository;

import com.example.appapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Boolean existsByStreetAndHomeNumber(String street, String homeNumber);
    Boolean existsByStreetAndHomeNumberAndIdNot(String street, String homeNumber, Integer id);
}
