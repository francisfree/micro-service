package com.example.card.entity;

import com.example.card.datatypes.CounterType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "counters", schema = "public")
@NoArgsConstructor
@SQLRestriction(value = "deleted = false")
public class Counter extends AbstractAuditableEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "counter_type", nullable = false, updatable = false)
    private CounterType counterType;

    @Column(name = "counter_current", nullable = false)
    private Long current;

    public Counter(CounterType counterType) {
        this.counterType = counterType;
    }

}
