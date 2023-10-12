package com.nnamdi.generator.domain.request;

import com.nnamdi.generator.utils.ValidatePin;
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
    @Size(max = 5)
    @ValidatePin()
    private String pin;
}
