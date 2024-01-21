package com.example.card.service;

import com.example.card.datatypes.CounterType;
import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.UpdateAccountDto;
import com.example.card.entity.Account;
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

    private final CounterService counterService;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Override
    public Account createAccount(CreateAccountDto createAccountDto) {
        String accountId = String.valueOf(counterService.getNextCounter(CounterType.Account));

        if (accountRepository.findByAccountIdIgnoreCase(accountId).isPresent()) {
            log.warn("create account :: accountId {} already exist ", accountId);
            throw new IllegalArgumentException("Account Id already exist");
        }

        Account account = new Account();
        account.setAccountId(accountId);
        account.setBicSwift(createAccountDto.getBicSwift());
        account.setIban(createAccountDto.getIban());
        account.setClientId(createAccountDto.getClientId());

        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(String accountId, UpdateAccountDto updateAccountDto) {
        Account account = getAccount(accountId);

        account.setIban(updateAccountDto.getIban());
        account.setBicSwift(updateAccountDto.getBicSwift());

        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String accountId) {
        return accountRepository.findByAccountIdIgnoreCase(accountId).orElseThrow(() -> new IllegalArgumentException("Account does not exist"));
    }

    @Override
    public Page<Account> getAccounts(String clientId, Pageable pageable) {

        //retrieve accounts by clientId if provided
        if (!ObjectUtils.isEmpty(clientId)) {
            return accountRepository.findByClientId(clientId, pageable);
        }

        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(String accountId) {
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
