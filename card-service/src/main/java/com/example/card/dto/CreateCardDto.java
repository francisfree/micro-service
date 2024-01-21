package com.example.card.dto;

import com.example.card.datatypes.CardType;
import com.example.card.docs.Examples;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(example = Examples.CREATE_CARD_REQUEST)
public class CreateCardDto {

    @NotBlank
    @Size(max = 250)
    private String alias;

    @NotNull
    private CardType type;

    @NotBlank
    @Size(max = 250)
    private String accountId;
}
