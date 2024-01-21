package com.example.card.entity;

import com.example.card.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts", schema = "public")
@NoArgsConstructor
@SQLRestriction(value = "deleted = false")
public class Account extends AbstractAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false, updatable = false)
    @JsonView({BaseView.BaseEntityDetailedView.class})
    private Long accountId;

    @Column(name = "iban", nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String iban;

    @Column(name = "bic_swift", nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String bicSwift;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", updatable = false, nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private Client client;

    @OneToMany(mappedBy = "account")
    @JsonView({BaseView.AccountDetailedView.class})
    private List<Card> cards = new ArrayList<>();

}
