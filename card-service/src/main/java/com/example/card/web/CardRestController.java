package com.example.card.web;

import com.example.card.docs.Examples;
import com.example.card.dto.CreateCardDto;
import com.example.card.dto.UpdateCardDto;
import com.example.card.entity.Card;
import com.example.card.service.CardService;
import com.example.card.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@Tag(name = "cards")
@RequiredArgsConstructor
public class CardRestController {
    private final CardService cardService;

    @PostMapping
    @JsonView(BaseView.CardDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "20O", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CARD_OK_RESPONSE)))
    })
    public Card createCard(@RequestBody @Valid CreateCardDto createCardDto) {
        return cardService.createCard(createCardDto);
    }

    @PutMapping("/{cardId}")
    @JsonView(BaseView.CardDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "20O", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CARD_OK_RESPONSE)))
    })
    public Card updateCard(@PathVariable @Parameter(example = "808001") String cardId,
                           @RequestBody @Valid UpdateCardDto updateCardDto) {
        return cardService.updateCard(cardId, updateCardDto);
    }

    @GetMapping
    @JsonView(BaseView.CardDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CARDS_OK_RESPONSE)))
    })
    @PageableAsQueryParam
    public Page<Card> getCards(@RequestParam(value = "accountId", required = false) String accountId,
                               @Parameter(hidden = true) Pageable pageable) {
        return cardService.getCards(accountId, pageable);
    }

    @GetMapping("/{cardId}")
    @JsonView(BaseView.CardDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CARD_OK_RESPONSE)))
    })
    public Card getCard(@PathVariable @Parameter(example = "808005") String cardId) {
        return cardService.getCard(cardId);
    }

    @DeleteMapping("/{cardId}")
    @JsonView(BaseView.CardDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public void deleteCard(@PathVariable @Parameter(example = "808004") String cardId) {
        cardService.deleteCard(cardId);
    }
}
