package com.markin.studentmanagement.infrastructure.adapters.input.rest.dto;

import com.markin.studentmanagement.infrastructure.adapters.input.rest.util.ValidDateOfBirth;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Builder
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
    private String dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirthString) {
        if (dateOfBirthString == null || dateOfBirthString.isEmpty()) {
            throw new IllegalArgumentException("Date of birth cannot be empty");
        }

        try {

            LocalDate parsedDate = LocalDate.parse(dateOfBirthString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (parsedDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date of birth cannot be in the future");
            }
            this.dateOfBirth = parsedDate.atStartOfDay().toString();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD");
        }
    }
}
