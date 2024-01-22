package com.example.card.service;

import com.example.card.dto.CreateClientDto;
import com.example.card.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Client createClient(CreateClientDto createClientDto);

    Client getClient(Long clientId);

    Page<Client> getClients(Pageable pageable);
}
