package com.example.card.web;

import com.example.card.docs.Examples;
import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.UpdateAccountDto;
import com.example.card.entity.Account;
import com.example.card.entity.Card;
import com.example.card.service.AccountService;
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
@RequestMapping("/accounts")
@Tag(name = "accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;
    private final CardService cardService;

    @PostMapping
    @JsonView(BaseView.AccountDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "20O", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.ACCOUNT_OK_RESPONSE)))
    })
    public Account createAccount(@RequestBody @Valid CreateAccountDto createAccountDto) {
        return accountService.createAccount(createAccountDto);
    }

    @PutMapping("{accountId}")
    @JsonView(BaseView.AccountDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "20O", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.ACCOUNT_OK_RESPONSE)))
    })
    public Account updateAccount(@PathVariable @Parameter(example = "12002") String accountId,
                                 @RequestBody @Valid UpdateAccountDto updateAccountDto) {
        return accountService.updateAccount(accountId, updateAccountDto);
    }

    @GetMapping
    @JsonView(BaseView.AccountDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.ACCOUNTS_OK_RESPONSE)))
    })
    @PageableAsQueryParam
    public Page<Account> getAccounts(@RequestParam(value = "clientId", required = false) String clientId,
                                     @Parameter(hidden = true) Pageable pageable) {
        return accountService.getAccounts(clientId, pageable);
    }

    @GetMapping("/{accountId}")
    @JsonView(BaseView.AccountDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.ACCOUNT_OK_RESPONSE)))
    })
    public Account getAccount(@PathVariable @Parameter(example = "12001") String accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/{accountId}/cards")
    @JsonView(BaseView.CardDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CARDS_OK_RESPONSE)))
    })
    @PageableAsQueryParam
    public Page<Card> getCards(@PathVariable(value = "accountId") @Parameter(example = "12001") String accountId,
                               @Parameter(hidden = true) Pageable pageable) {

        return cardService.getCards(accountId, pageable);
    }

    @DeleteMapping("{accountId}")
    @JsonView(BaseView.AccountDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public void deleteAccount(@PathVariable  @Parameter(example = "12003") String accountId) {
        accountService.deleteAccount(accountId);
    }
}
