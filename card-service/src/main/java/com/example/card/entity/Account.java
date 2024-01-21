package com.example.card.entity;

import com.example.card.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Column(name = "account_id", nullable = false, updatable = false, unique = true)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String accountId;

    @Column(name = "iban", nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String iban;

    @Column(name = "bic_swift", nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String bicSwift;

    @Column(name = "client_id", nullable = false, updatable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class})
    private String clientId;

    @OneToMany(mappedBy = "account")
    @JsonView({BaseView.AccountDetailedView.class})
    private List<Card> cards = new ArrayList<>();

}
