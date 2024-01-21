package com.example.card.service;

import com.example.card.datatypes.CounterType;
import com.example.card.dto.CreateCardDto;
import com.example.card.dto.UpdateCardDto;
import com.example.card.entity.Account;
import com.example.card.entity.Card;
import com.example.card.repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    
    private final CounterService counterService;
    private final CardRepository cardRepository;
    private final AccountService accountService;

    @Override
    public Card createCard(CreateCardDto createCardDto) {
        String cardId = String.valueOf(counterService.getNextCounter(CounterType.Card));

        if (cardRepository.findByCardIdIgnoreCase(cardId).isPresent()) {
            log.warn("create card :: cardId {} already exist ", cardId);
            throw new IllegalArgumentException("Card Id already exist");
        }

        Account account = accountService.getAccount(createCardDto.getAccountId());

        Card card = new Card();
        card.setCardId(cardId);
        card.setAlias(createCardDto.getAlias());
        card.setType(createCardDto.getType());
        card.setAccount(account);

        return cardRepository.save(card);
    }

    @Override
    public Card updateCard(String cardId, UpdateCardDto updateCardDto) {
        Card card = getCard(cardId);

        card.setAlias(updateCardDto.getAlias());

        return cardRepository.save(card);
    }

    @Override
    public Card getCard(String cardId) {
        return cardRepository.findByCardIdIgnoreCase(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found"));
    }

    @Override
    public Page<Card> getCards(String accountId, Pageable pageable) {
        //return cards associated to an accountId
        if (!ObjectUtils.isEmpty(accountId)) {
            Account account = accountService.getAccount(accountId);
            return cardRepository.findByAccount(account, pageable);
        } else {
            return cardRepository.findAll(pageable);
        }
    }

    @Override
    public void deleteCard(String cardId) {
        Card card = getCard(cardId);

        card.setDeleted(true);

        cardRepository.save(card);
    }
}
