package com.moduscreate.bankaccount;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/balances")
public class BalanceResource {

    private final Balances balances;

    @Inject
    public BalanceResource(Balances balances) {
        this.balances = balances;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findByAccountId(@QueryParam("accountId") String accountId) {
        return balances.findByAccountId(accountId).map(balance -> Response.ok(balance).build())
                .orElseThrow(IllegalArgumentException::new);
    }

}