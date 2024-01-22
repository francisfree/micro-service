package com.example.card.entity;

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
@Table(name = "clients", schema = "public")
@NoArgsConstructor
@SQLRestriction(value = "deleted = false")
public class Client extends AbstractAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, updatable = false)
    @JsonView({BaseView.BaseEntityDetailedView.class})
    private Long clientId;

    @Column(name = "client_name", nullable = false)
    @JsonView({BaseView.AccountDetailedView.class, BaseView.CardDetailedView.class, BaseView.ClientDetailedView.class})
    private String name;
}
