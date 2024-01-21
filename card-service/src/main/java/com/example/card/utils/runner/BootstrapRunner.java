package com.example.card.utils.runner;

import com.example.card.datatypes.CardType;
import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.CreateCardDto;
import com.example.card.dto.CreateClientDto;
import com.example.card.entity.Account;
import com.example.card.entity.Client;
import com.example.card.repository.AccountRepository;
import com.example.card.repository.CardRepository;
import com.example.card.repository.ClientRepository;
import com.example.card.service.AccountService;
import com.example.card.service.CardService;
import com.example.card.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
public class BootstrapRunner implements CommandLineRunner {

    @Value("${application.bootstrap.data}")
    private String runBootStrap;

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final ClientService clientService;
    private final CardService cardService;

    @Autowired
    public BootstrapRunner(AccountRepository accountRepository, ClientRepository clientRepository, CardRepository cardRepository, AccountService accountService, ClientService clientService, CardService cardService) {

        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.cardRepository = cardRepository;
        this.accountService = accountService;
        this.clientService = clientService;
        this.cardService = cardService;
    }

    @Override
    public void run(String... args) {
        if (Boolean.parseBoolean(runBootStrap)) {
            log.info("Start Bootstrap");

            if (clientRepository.findAll().isEmpty()) {

                Client clientA = createClient("Client A");
                Client clientB = createClient("Client B");
                Client clientC = createClient("Client C");

                if (accountRepository.findAll().isEmpty()) {
                    Account account1 = createAccount(clientA, "KE2110001", "ABCLKENA");
                    Account account2 = createAccount(clientB, "KE2110002", "AFRIKENX");
                    Account account3 = createAccount(clientC, "KE2110003", "BARBKENA");

                    if (cardRepository.findAll().isEmpty()) {
                        createCard(account1, "Person A", CardType.Virtual);
                        createCard(account3, "Person B", CardType.Virtual);
                        createCard(account3, "Person C", CardType.Physical);
                        createCard(account2, "Person D", CardType.Virtual);
                        createCard(account2, "Person E", CardType.Physical);
                    }
                }
            }
            log.info("Done Bootstrap");
        }
    }

    private Client createClient(String clientName) {
        CreateClientDto createClientDto = new CreateClientDto(clientName);

        return clientService.createClient(createClientDto);
    }

    private void createCard(Account account, String alias, CardType cardType) {
        CreateCardDto createCardDto = new CreateCardDto();
        createCardDto.setAccountId(account.getAccountId());
        createCardDto.setAlias(alias);
        createCardDto.setType(cardType);

        cardService.createCard(createCardDto);
    }

    private Account createAccount(Client client, String iban, String bicSwift) {
        CreateAccountDto createAccountDto = new CreateAccountDto();
        createAccountDto.setClientId(client.getClientId());
        createAccountDto.setIban(iban);
        createAccountDto.setBicSwift(bicSwift);

        return accountService.createAccount(createAccountDto);
    }
}
