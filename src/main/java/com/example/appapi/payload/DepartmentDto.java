package com.example.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentDto {
    @NotNull(message = "Name not entered!")
    private String name;
    @NotNull(message = "Company not entered!")
    private Integer company;
}
