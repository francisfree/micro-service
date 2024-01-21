package com.example.card.service;

import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.UpdateAccountDto;
import com.example.card.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Account createAccount(CreateAccountDto createAccountDto);

    Account updateAccount(String accountId, UpdateAccountDto updateAccountDto);

    Account getAccount(String accountId);

    Page<Account> getAccounts(String clientId, Pageable pageable);

    void deleteAccount(String accountId);
}
