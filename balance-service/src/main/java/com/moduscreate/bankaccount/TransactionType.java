package com.moduscreate.bankaccount;

public enum TransactionType {

    INCOME, EXPENSE;

    public boolean isIncome() {
        return this == INCOME;
    }

}