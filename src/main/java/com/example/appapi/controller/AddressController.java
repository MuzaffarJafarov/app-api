package com.example.appapi.controller;

import com.example.appapi.entity.ApiResponse;
import com.example.appapi.payload.AddressDto;
import com.example.appapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.getIsSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse allAddress = addressService.getAllAddress();
        return ResponseEntity.status(allAddress.getIsSuccess() ? 200 : 404).body(allAddress);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse addressById = addressService.getAddressById(id);
        return ResponseEntity.status(addressById.getIsSuccess() ? 200 : 404).body(addressById);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.deleteAddressById(id);
        return ResponseEntity.status(apiResponse.getIsSuccess() ? 200 : 409).body(addressService);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@RequestBody AddressDto addressDto, @PathVariable Integer id) {
        ApiResponse apiResponse = addressService.editAddressById(addressDto, id);
        return ResponseEntity.status(apiResponse.getIsSuccess() ? 200 : 409).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
