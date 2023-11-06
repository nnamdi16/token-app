package com.nnamdi.generator.domain.request;

import com.nnamdi.generator.utils.ValidatePin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(max = 5, min = 5, message = "Pin must be 5 digits")
    @Pattern(regexp = "\\d+", message = "Only digits are allowed")
    @ValidatePin()
    private String pin;
}
