package com.example.card.repository;

import com.example.card.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountIdIgnoreCase(String accountId);

    Page<Account> findByClientId(String clientId, Pageable pageable);
}
