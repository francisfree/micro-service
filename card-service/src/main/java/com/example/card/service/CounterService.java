package com.example.card.service;

import com.example.card.datatypes.CounterType;

public interface CounterService {

    Long getNextCounter(CounterType counterType);
}
