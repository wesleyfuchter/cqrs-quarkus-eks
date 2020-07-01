package com.moduscreate.bankaccount;

import java.net.URI;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/transactions")
public class TransactionResource {

    private final Emitter<Transaction> emitter;

    @Inject
    public TransactionResource(@Channel("transactions") Emitter<Transaction> emitter) {
        this.emitter = emitter;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Transaction transaction) {
        transaction.persist();
        emitter.send(transaction);
        return Response.created(URI.create("/transactions/" + transaction.id)).build();
    }
    
}