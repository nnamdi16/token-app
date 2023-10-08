package com.nnamdi.generator.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateTokenRequestDto implements Serializable {
    /**
     * Assumption that the maximum number of digits are 5
     */
    @NotBlank(message = "pin must be provided")
    @Max(value = 5, message = "maximum number of pin should be 5 ")
    private String pin;
}
