package com.example.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressDto {
    @NotNull(message = "{Street should not be empty!}")
    private String street;
    @NotNull(message = "Home number should not be empty!")
    private String homeNumber;
}
