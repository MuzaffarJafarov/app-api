package com.example.appapi.repository;

import com.example.appapi.entity.Company;
import com.example.appapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Boolean existsByNameAndCompany(String name, Company company);
    Boolean existsByNameAndCompanyAndIdNot(String name, Company company, Integer id);
}
