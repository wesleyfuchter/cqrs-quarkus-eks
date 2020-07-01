package com.moduscreate.bankaccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "balances")
public class Balance extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    public String id;

    @Column(name = "account_id")
    public String accountId;

    @Column
    public double value;

    public Balance recalculateBalance(Transaction transaction) {
        if (transaction.isIncome()) {
            this.value += transaction.getValue();
        } else {
            this.value -= transaction.getValue();
        }
        return this;
    }

    public static Balance defaultInstance(String accountId) {
        var balance = new Balance();
        balance.accountId = accountId;
        return balance;
    }

}
