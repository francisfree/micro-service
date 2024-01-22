package com.example.card.service;

import com.example.card.dto.CreateCardDto;
import com.example.card.dto.UpdateCardDto;
import com.example.card.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    Card createCard(CreateCardDto createCardDto);

    Card updateCard(Long cardId, UpdateCardDto updateCardDto);

    Card getCard(Long cardId);

    Page<Card> getCards(Long accountId, Pageable pageable);

    void deleteCard(Long cardId);
}
