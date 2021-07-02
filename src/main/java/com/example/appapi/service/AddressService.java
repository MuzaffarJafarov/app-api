package com.example.appapi.service;

import com.example.appapi.entity.Address;
import com.example.appapi.entity.ApiResponse;
import com.example.appapi.payload.AddressDto;
import com.example.appapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        Boolean aBoolean = addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber());
        if (aBoolean)
            return new ApiResponse("This address already exists! ", false);
        addressRepository.save(address);
        return new ApiResponse("Address added successfully!", true);
    }

    public ApiResponse getAllAddress() {
        List<Address> all = addressRepository.findAll();
        return new ApiResponse("Successfully found!", true, all);
    }

    public ApiResponse getAddressById(Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Address not found!", false, null);
        Address address = byId.get();
        return new ApiResponse("Address found! ", true, address);
    }

    public ApiResponse deleteAddressById(Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Address not found!", false);
        addressRepository.delete(byId.get());
        return new ApiResponse("Address deleted! ", true);
    }

    public ApiResponse editAddressById(AddressDto addressDto, Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Address not found!", false);
        Address address = byId.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        Boolean aBoolean = addressRepository.existsByStreetAndHomeNumberAndIdNot(addressDto.getStreet(), addressDto.getHomeNumber(), id);
        if (aBoolean)
            return new ApiResponse("This address already exists! ", false);
        addressRepository.save(address);
        return new ApiResponse("Address changed successfully!", true);
    }
}
