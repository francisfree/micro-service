package com.example.card.utils.views;

public interface BaseView {

    interface BaseEntityListView {}

    interface BaseEntityDetailedView extends BaseEntityListView {}

    interface CardDetailedView extends BaseEntityDetailedView {}

    interface AccountDetailedView extends BaseEntityDetailedView {}
}
