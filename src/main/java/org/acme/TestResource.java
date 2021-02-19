package org.acme;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestResource {

    private static final Logger log = LoggerFactory.getLogger(TestResource.class);

    @Inject
    @Channel("test")
    Emitter<String> emitter;

    @Inject
    TransactionManager transactionManager;

    @POST
    @Transactional
    public Response hello() throws Exception {
        log.info("tx={}", transactionManager.getTransaction());
        emitter.send("test");
        return Response.ok().build();
    }
}