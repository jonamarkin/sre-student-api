package com.markin.studentmanagement.infrastructure.adapters.input.rest.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true;
        }
        return !dateOfBirth.isAfter(LocalDateTime.now());
    }
}