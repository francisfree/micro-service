package com.example.card.utils.runner;

import com.example.card.datatypes.CardType;
import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.CreateCardDto;
import com.example.card.entity.Account;
import com.example.card.repository.AccountRepository;
import com.example.card.repository.CardRepository;
import com.example.card.service.AccountService;
import com.example.card.service.CardService;
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
    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final CardService cardService;

    @Autowired
    public BootstrapRunner(AccountRepository accountRepository, CardRepository cardRepository, AccountService accountService, CardService cardService) {

        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.accountService = accountService;
        this.cardService = cardService;
    }

    @Override
    public void run(String... args) {
        if (Boolean.parseBoolean(runBootStrap)) {
            log.info("Start Bootstrap");

            if (accountRepository.findAll().isEmpty()) {
                Account account1 = createAccount("61894943737", "KE2110001", "ABCLKENA");
                Account account2 = createAccount("62894943737", "KE2110002", "AFRIKENX");
                Account account3 = createAccount("63894943737", "KE2110003", "BARBKENA");

                if (cardRepository.findAll().isEmpty()) {
                    createCard(account1, "Person A", CardType.Virtual);
                    createCard(account3, "Person B", CardType.Virtual);
                    createCard(account3, "Person C", CardType.Physical);
                    createCard(account2, "Person D", CardType.Virtual);
                    createCard(account2, "Person E", CardType.Physical);
                }
            }

            log.info("Done Bootstrap");
        }

    }

    private void createCard(Account account, String alias, CardType cardType) {
        CreateCardDto createCardDto = new CreateCardDto();
        createCardDto.setAccountId(account.getAccountId());
        createCardDto.setAlias(alias);
        createCardDto.setType(cardType);

        cardService.createCard(createCardDto);
    }

    private Account createAccount(String clientId, String iban, String bicSwift) {
        CreateAccountDto createAccountDto = new CreateAccountDto();
        createAccountDto.setClientId(clientId);
        createAccountDto.setIban(iban);
        createAccountDto.setBicSwift(bicSwift);

        return accountService.createAccount(createAccountDto);
    }
}
