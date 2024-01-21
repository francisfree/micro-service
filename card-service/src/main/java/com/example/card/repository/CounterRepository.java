package com.example.card.repository;

import com.example.card.datatypes.CounterType;
import com.example.card.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {

    Optional<Counter> findByCounterType(CounterType counterType);
}
