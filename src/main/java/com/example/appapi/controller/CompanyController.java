package com.example.appapi.controller;

import com.example.appapi.entity.ApiResponse;
import com.example.appapi.payload.AddressDto;
import com.example.appapi.payload.CompanyDto;
import com.example.appapi.service.AddressService;
import com.example.appapi.service.CompanyService;
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
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.getIsSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse allCompanies = companyService.getAllCompanies();
        return ResponseEntity.status(allCompanies.getIsSuccess() ? 200 : 404).body(allCompanies);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse companyById = companyService.getCompanyById(id);
        return ResponseEntity.status(companyById.getIsSuccess() ? 200 : 404).body(companyById);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse deleteCompanyById = companyService.deleteCompanyById(id);
        return ResponseEntity.status(deleteCompanyById.getIsSuccess() ? 200 : 409).body(deleteCompanyById);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@Valid @RequestBody CompanyDto companyDto,@PathVariable Integer id) {
        ApiResponse apiResponse = companyService.editCompanyById(companyDto, id);
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
