package com.example.card.service;

import com.example.card.dto.CreateCardDto;
import com.example.card.dto.UpdateCardDto;
import com.example.card.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    Card createCard(CreateCardDto createCardDto);

    Card updateCard(String cardId, UpdateCardDto updateCardDto);

    Card getCard(String cardId);

    Page<Card> getCards(String accountId, Pageable pageable);

    void deleteCard(String cardId);
}
