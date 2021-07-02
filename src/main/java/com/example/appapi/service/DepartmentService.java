package com.example.appapi.service;

import com.example.appapi.entity.Address;
import com.example.appapi.entity.ApiResponse;
import com.example.appapi.entity.Company;
import com.example.appapi.entity.Department;
import com.example.appapi.payload.CompanyDto;
import com.example.appapi.payload.DepartmentDto;
import com.example.appapi.repository.AddressRepository;
import com.example.appapi.repository.CompanyRepository;
import com.example.appapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse addDepartment(DepartmentDto departmentDto) {
        Optional<Company> companyById = companyRepository.findById(departmentDto.getCompany());
        if (!companyById.isPresent())
            return new ApiResponse("Company not found", false);
        Boolean aBoolean = departmentRepository.existsByNameAndCompany(departmentDto.getName(), companyById.get());
        if (aBoolean)
            return new ApiResponse("This Department already exists!", false);
        Department department = new Department();
        department.setCompany(companyById.get());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse getAllDepartments() {
        List<Department> all = departmentRepository.findAll();
        return new ApiResponse("Successfully found!", true, all);
    }

    public ApiResponse getDepartmentById(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Department not found!", false, null);
        Department department = byId.get();
        return new ApiResponse("Department found! ", true, department);
    }

    public ApiResponse deleteDepartmentById(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Department not found!", false);
        departmentRepository.delete(byId.get());
        return new ApiResponse("Department deleted! ", true);
    }

    public ApiResponse editDepartmentById(DepartmentDto departmentDto, Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Department not found!", false);
        Optional<Company> companyById = companyRepository.findById(departmentDto.getCompany());
        if (!companyById.isPresent())
            return new ApiResponse("Company not found", false);
        Boolean aBoolean = departmentRepository.existsByNameAndCompanyAndIdNot(departmentDto.getName(), companyById.get(), id);
        if (aBoolean)
            return new ApiResponse("This department already exist!", false);
        Department department = byId.get();
        department.setName(department.getName());
        department.setCompany(companyById.get());
        departmentRepository.save(department);
        return new ApiResponse("Department changed successfully!", true);
    }
}
