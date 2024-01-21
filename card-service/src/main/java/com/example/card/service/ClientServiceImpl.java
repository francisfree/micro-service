package com.example.card.service;

import com.example.card.dto.CreateClientDto;
import com.example.card.entity.Client;
import com.example.card.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client createClient(CreateClientDto createClientDto) {
        Client client = new Client();
        client.setName(createClientDto.getName());

        return clientRepository.save(client);
    }

    @Override
    public Client getClient(Long clientId) {
        return clientRepository.findByClientId(clientId).orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    @Override
    public Page<Client> getClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }
}
