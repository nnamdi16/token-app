package com.nnamdi.validator.domain.request;


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
public class ValidateTokenRequestDto implements Serializable {
    @NotBlank(message = "token must be provided")
    private String token;
}
