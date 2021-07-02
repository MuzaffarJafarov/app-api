package com.example.appapi.repository;

import com.example.appapi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Boolean existsByCorpNameAndDirectorName(String corpName, String directorName);
    Boolean existsByCorpNameAndDirectorNameAndIdNot(String corpName, String directorName, Integer id);
}
