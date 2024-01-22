package com.example.card.repository;

import com.example.card.entity.Account;
import com.example.card.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardId(Long cardId);

    Page<Card> findByAccount(Account account, Pageable pageable);

    List<Card> findByAccount(Account account);
}
