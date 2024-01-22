package com.example.card.service;

import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.UpdateAccountDto;
import com.example.card.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Account createAccount(CreateAccountDto createAccountDto);

    Account updateAccount(Long accountId, UpdateAccountDto updateAccountDto);

    Account getAccount(Long accountId);

    Page<Account> getAccounts(Long clientId, Pageable pageable);

    void deleteAccount(Long accountId);
}
