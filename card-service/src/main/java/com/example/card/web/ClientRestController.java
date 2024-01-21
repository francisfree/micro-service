package com.example.card.web;

import com.example.card.docs.Examples;
import com.example.card.dto.CreateClientDto;
import com.example.card.entity.Client;
import com.example.card.service.ClientService;
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
@RequestMapping("/clients")
@Tag(name = "clients")
@RequiredArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

    @PostMapping
    @JsonView(BaseView.ClientDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "20O", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CLIENT_OK_RESPONSE)))
    })
    public Client createClient(@RequestBody @Valid CreateClientDto createClientDto) {
        return clientService.createClient(createClientDto);
    }

    @GetMapping 
    @JsonView(BaseView.ClientDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CLIENTS_OK_RESPONSE)))
    })
    @PageableAsQueryParam
    public Page<Client> getClients(@Parameter(hidden = true) Pageable pageable) {
        return clientService.getClients(pageable);
    }

    @GetMapping("/{clientId}")
    @JsonView(BaseView.ClientDetailedView.class)
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.CLIENT_OK_RESPONSE)))
    })
    public Client getClient(@PathVariable @Parameter(example = "1") Long clientId) {
        return clientService.getClient(clientId);
    }

}
