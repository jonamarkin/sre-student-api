package com.markin.studentmanagement.infrastructure.adapters.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {
    private String status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
