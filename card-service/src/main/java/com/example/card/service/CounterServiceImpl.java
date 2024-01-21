package com.example.card.service;

import com.example.card.datatypes.CounterType;
import com.example.card.entity.Counter;
import com.example.card.repository.CounterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    @Transactional
    @Override
    public Long getNextCounter(CounterType counterType) {
        Optional<Counter> optionalCounter = counterRepository.findByCounterType(counterType);

        if (optionalCounter.isPresent()) {
            return numericIncrement(optionalCounter.get()).getCurrent();
        }

        Counter counter = create(counterType);
        return counter.getCurrent();
    }

    private Counter create(CounterType counterType) {
        Counter counter = new Counter(counterType);
        if (counterType == CounterType.Account) {
            counter.setCurrent(12001L);
        } else if (counterType == CounterType.Card) {
            counter.setCurrent(808001L);
        } else {
            counter.setCurrent(1001L);
        }
        return counterRepository.save(counter);
    }

    private Counter numericIncrement(Counter counter) {
        counter.setCurrent(counter.getCurrent() + 1);
        return counterRepository.save(counter);
    }

}
