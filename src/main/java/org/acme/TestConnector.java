package org.acme;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import org.eclipse.microprofile.reactive.messaging.spi.OutgoingConnectorFactory;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.eclipse.microprofile.reactive.streams.operators.SubscriberBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

@ApplicationScoped
@Connector("test")
public class TestConnector implements OutgoingConnectorFactory {

    private static final Logger log = LoggerFactory.getLogger(TestConnector.class);

    @Inject
    TransactionManager transactionManager;

    @Override
    public SubscriberBuilder<? extends Message<?>, Void> getSubscriberBuilder(Config config) {
        return ReactiveStreams.<Message<?>>builder()
                .flatMapCompletionStage(message -> {
                    try {
                        log.info("tx={}", transactionManager.getTransaction());
                    }
                    catch (SystemException e) {
                        log.error("Eww :-/", e);
                    }
                    return message.ack();
                })
                .ignore();
    }
}
