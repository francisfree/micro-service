package com.example.card.entity;

import com.example.card.datatypes.CardType;
import com.example.card.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "cards", schema = "public")
@NoArgsConstructor
@SQLRestriction(value = "deleted = false")
public class Card extends AbstractAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false, updatable = false)
    @JsonView({BaseView.BaseEntityDetailedView.class})
    private Long cardId;

    @Column(name = "card_alias", nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String alias;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false, updatable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private CardType type;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable = false, nullable = false)
    @JsonView({BaseView.CardDetailedView.class})
    private Account account;
}
