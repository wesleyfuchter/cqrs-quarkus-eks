package com.moduscreate.bankaccount;

import java.util.Map;

public class Transaction {

    private final String accountId;
    private final String description;
    private final TransactionType transactionType;
    private final double value;

    public Transaction(String accountId, String description, TransactionType transactionType, double value) {
        this.accountId = accountId;
        this.description = description;
        this.transactionType = transactionType;
        this.value = value;
    }

    public boolean isIncome() {
        return transactionType.isIncome();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Transaction ofMap(Map<String, Object> map) {
        return new Transaction(
                map.get("accountId").toString(),
                map.get("description").toString(),
                TransactionType.valueOf(map.get("type").toString()),
                Double.parseDouble(map.get("value").toString()));
    }

}
