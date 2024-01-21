package com.example.card.service;

import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.UpdateAccountDto;
import com.example.card.entity.Account;
import com.example.card.entity.Client;
import com.example.card.repository.AccountRepository;
import com.example.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ClientService clientService;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Override
    public Account createAccount(CreateAccountDto createAccountDto) {

        Client client = clientService.getClient(createAccountDto.getClientId());

        Account account = new Account();
        account.setBicSwift(createAccountDto.getBicSwift());
        account.setIban(createAccountDto.getIban());
        account.setClient(client);

        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, UpdateAccountDto updateAccountDto) {
        Account account = getAccount(accountId);

        account.setIban(updateAccountDto.getIban());
        account.setBicSwift(updateAccountDto.getBicSwift());

        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountRepository.findByAccountId(accountId).orElseThrow(() -> new IllegalArgumentException("Account does not exist"));
    }

    @Override
    public Page<Account> getAccounts(Long clientId, Pageable pageable) {

        //retrieve accounts by clientId if provided
        if (!ObjectUtils.isEmpty(clientId)) {
            Client client = clientService.getClient(clientId);
            return accountRepository.findByClient(client, pageable);
        }

        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = getAccount(accountId);

        //delete cards linked to account
        cardRepository.findByAccount(account).forEach(card -> {
            card.setDeleted(true);
            cardRepository.save(card);
        });

        account.setDeleted(true);
        accountRepository.save(account);

    }
}
