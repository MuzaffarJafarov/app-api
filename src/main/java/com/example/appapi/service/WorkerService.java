package com.example.appapi.service;

import com.example.appapi.entity.*;
import com.example.appapi.payload.DepartmentDto;
import com.example.appapi.payload.WorkerDto;
import com.example.appapi.repository.AddressRepository;
import com.example.appapi.repository.CompanyRepository;
import com.example.appapi.repository.DepartmentRepository;
import com.example.appapi.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    WorkerRepository workerRepository;

    public ApiResponse addWorker(WorkerDto workerDto) {
        Optional<Department> department = departmentRepository.findById(workerDto.getDepartment());
        if (!department.isPresent())
            return new ApiResponse("Department not found", false);
        Optional<Address> address = addressRepository.findById(workerDto.getAddress());
        if (!department.isPresent())
            return new ApiResponse("Address not found", false);
        Boolean phoneNumberBoolean = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (phoneNumberBoolean)
            return new ApiResponse("This Phone number already exist!", false);
        Worker worker = new Worker();
        worker.setAddress(address.get());
        worker.setDepartment(department.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse getAllWorkers() {
        List<Worker> all = workerRepository.findAll();
        return new ApiResponse("Successfully found!", true, all);
    }

    public ApiResponse getWorkerById(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Worker not found!", false, null);
        Worker worker = byId.get();
        return new ApiResponse("Worker found! ", true, worker);
    }

    public ApiResponse deleteWorkerById(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Worker not found!", false);
        workerRepository.delete(byId.get());
        return new ApiResponse("Worker deleted successfully! ", true);
    }

    public ApiResponse editWorkerById(WorkerDto workerDto, Integer id) {
        Optional<Department> department = departmentRepository.findById(workerDto.getDepartment());
        if (!department.isPresent())
            return new ApiResponse("Department not found!", false);
        Optional<Worker> workerById = workerRepository.findById(id);
        if (!workerById.isPresent())
            return new ApiResponse("Worker not found", false);
        Optional<Address> address = addressRepository.findById(workerDto.getAddress());
        if (!address.isPresent())
            return new ApiResponse("Address not found! ",false);
        Boolean phoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (phoneNumberAndIdNot)
            return new ApiResponse("This phone number already exist!",false);
        Worker worker = workerById.get();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());
        worker.setAddress(address.get());
        worker.setDepartment(department.get());
        workerRepository.save(worker);
        return new ApiResponse("Worker changed successfully!", true);
    }
}
