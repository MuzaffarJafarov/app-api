package com.example.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkerDto {
    @NotNull(message = "{ should not be empty!}")
    private String name;
    @NotNull(message = "Phone number should not be empty!")
    private String phoneNumber;
    @NotNull(message = "Address should not be empty!")
    private Integer address;
    private Integer department;
}
