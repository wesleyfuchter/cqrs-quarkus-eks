package com.moduscreate.bankaccount;

import java.util.Optional;
import javax.transaction.Transactional;

public interface Balances {

    Optional<Balance> findByAccountId(String accountId);

    @Transactional
    Balance recalculate(Transaction transaction);

}
