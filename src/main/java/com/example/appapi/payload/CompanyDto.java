package com.example.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompanyDto {
    @NotNull
    private String corpName;
    @NotNull
    private String directorName;
    @OneToOne(optional = false)
    private Integer address;
}
