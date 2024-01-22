package com.example.card.dto;

import com.example.card.docs.Examples;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(example = Examples.UPDATE_ACCOUNT_REQUEST)
public class UpdateAccountDto {
    @NotBlank
    @Size(max = 250)
    private String iban;

    @NotBlank
    @Size(max = 250)
    private String bicSwift;
}
