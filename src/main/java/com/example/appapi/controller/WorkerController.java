package com.example.appapi.controller;

import com.example.appapi.entity.ApiResponse;
import com.example.appapi.payload.DepartmentDto;
import com.example.appapi.payload.WorkerDto;
import com.example.appapi.service.DepartmentService;
import com.example.appapi.service.WorkerService;
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
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.getIsSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse allWorkers = workerService.getAllWorkers();
        return ResponseEntity.status(allWorkers.getIsSuccess() ? 200 : 404).body(allWorkers);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse workerById = workerService.getWorkerById(id);
        return ResponseEntity.status(workerById.getIsSuccess() ? 200 : 404).body(workerById);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse deleteWorkerById = workerService.deleteWorkerById(id);
        return ResponseEntity.status(deleteWorkerById.getIsSuccess() ? 200 : 409).body(deleteWorkerById);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@Valid @RequestBody WorkerDto workerDto,@PathVariable Integer id) {
        ApiResponse apiResponse = workerService.editWorkerById(workerDto, id);
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
