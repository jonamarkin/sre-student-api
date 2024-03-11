package com.markin.studentmanagement.infrastructure.adapters.input.rest.dto;

import com.markin.studentmanagement.infrastructure.adapters.input.rest.util.ValidDateOfBirth;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotEmpty(message = "firstName cannot be empty")
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @NotEmpty(message = "lastName cannot be empty")
    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "dateOfBirth cannot be null")
    @ValidDateOfBirth
    private LocalDateTime dateOfBirth;
}
