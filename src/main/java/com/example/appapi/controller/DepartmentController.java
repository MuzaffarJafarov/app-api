package com.example.appapi.controller;

import com.example.appapi.entity.ApiResponse;
import com.example.appapi.payload.CompanyDto;
import com.example.appapi.payload.DepartmentDto;
import com.example.appapi.service.CompanyService;
import com.example.appapi.service.DepartmentService;
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
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.getIsSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse allDepartments = departmentService.getAllDepartments();
        return ResponseEntity.status(allDepartments.getIsSuccess() ? 200 : 404).body(allDepartments);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.status(departmentById.getIsSuccess() ? 200 : 404).body(departmentById);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse deleteDepartmentById = departmentService.deleteDepartmentById(id);
        return ResponseEntity.status(deleteDepartmentById.getIsSuccess() ? 200 : 409).body(deleteDepartmentById);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@Valid @RequestBody DepartmentDto departmentDto,@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.editDepartmentById(departmentDto, id);
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
