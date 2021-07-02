package com.example.appapi.service;

import com.example.appapi.entity.Address;
import com.example.appapi.entity.ApiResponse;
import com.example.appapi.entity.Company;
import com.example.appapi.payload.AddressDto;
import com.example.appapi.payload.CompanyDto;
import com.example.appapi.repository.AddressRepository;
import com.example.appapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addCompany(CompanyDto companyDto) {
        Optional<Address> addressById = addressRepository.findById(companyDto.getAddress());
        if (!addressById.isPresent())
            return new ApiResponse("Address not found!", false);
        Boolean aBoolean = companyRepository.existsByCorpNameAndDirectorName(companyDto.getCorpName(), companyDto.getDirectorName());
        if (aBoolean)
            return new ApiResponse("This Company already exist! ", false);
        Company company = new Company();
        company.setAddress(addressById.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Company added successfully!", true);
    }

    public ApiResponse getAllCompanies() {
        List<Company> all = companyRepository.findAll();
        return new ApiResponse("Successfully found!", true, all);
    }

    public ApiResponse getCompanyById(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Company not found!", false, null);
        Company company = byId.get();
        return new ApiResponse("Company found! ", true, company);
    }

    public ApiResponse deleteCompanyById(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Company not found!", false);
        companyRepository.delete(byId.get());
        return new ApiResponse("Company deleted! ", true);
    }

    public ApiResponse editCompanyById(CompanyDto companyDto, Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Company not found!", false);
        Optional<Address> addressById1 = addressRepository.findById(companyDto.getAddress());
        if (!addressById1.isPresent())
            return new ApiResponse("Address not found!",false);
        Company company = byId.get();
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());
        company.setAddress(addressById1.get());
        return new ApiResponse("Address changed successfully!", true);
    }
}
