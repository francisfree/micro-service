package com.example.card.dto;

import com.example.card.docs.Examples;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(example = Examples.CREATE_CLIENT_REQUEST)
public class CreateClientDto {
    @NotBlank
    @Size(max = 250)
    private String name;
}
