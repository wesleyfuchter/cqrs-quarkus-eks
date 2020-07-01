package com.moduscreate.bankaccount;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import io.vertx.kafka.client.serialization.JsonObjectDeserializer;
import io.vertx.kafka.client.serialization.JsonObjectSerializer;

@ApplicationScoped
public class TransactionIncomeProducer {

    private final Logger log = Logger.getLogger(TransactionIncomeProducer.class.getName());

    private final Balances balances;

    @Inject
    public TransactionIncomeProducer(Balances balances) {
        this.balances = balances;
    }

    @Produces
    public Topology onTransactionTopology() {
        var builder = new StreamsBuilder();
        builder.stream("transactions",
                Consumed.with(Serdes.String(),
                        Serdes.serdeFrom(new JsonObjectSerializer(), new JsonObjectDeserializer())))
                .foreach((__, value) -> {
                    log.info("Receiving transaction " + value);
                    var transaction = Transaction.ofMap(value.getMap());
                    log.info("Receiving transaction with description " + transaction.getDescription());
                    balances.recalculate(transaction);
                });
        return builder.build();
    }


}
