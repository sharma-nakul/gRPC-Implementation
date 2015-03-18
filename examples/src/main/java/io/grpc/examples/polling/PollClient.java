package io.grpc.examples.polling;

import com.google.common.util.concurrent.SettableFuture;

import io.grpc.ChannelImpl;
import io.grpc.examples.polling.PollingGrpc.PollingBlockingStub;
import io.grpc.examples.polling.PollingGrpc.PollingStub;
import io.grpc.stub.StreamObserver;
import io.grpc.transport.netty.NegotiationType;
import io.grpc.transport.netty.NettyChannelBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class PollClient {
    private static final Logger logger = Logger.getLogger(PollClient.class.getName());

    private final ChannelImpl channel;
    private final PollingBlockingStub blockingStub;
    private final PollingStub asyncStub;

    public PollClient(String host, int port) {
        channel = NettyChannelBuilder.forAddress(host, port)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        blockingStub = PollingGrpc.newBlockingStub(channel);
        asyncStub = PollingGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTerminated(5, TimeUnit.SECONDS);
    }


    public void createPollService(String name, String question) {
        try {
            logger.info("Will try to post Poll " + name + " ...");
            PollRequest request = PollRequest
                                    .newBuilder()
                                    .setName(name)
                                    .setQuestion(question).build();
            PollResponse response = blockingStub.getPoll(request);
            logger.info("Created Poll: name = " + response.getName() + ", question = " + response.getQuestion());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return;
        }
    }



    public static void main(String[] args) throws Exception {
        PollClient client = new PollClient("localhost", 8080);
        try {
            String name = "";
            String question = "";
            if (args.length > 0) {
                name = args[0];
                question = args[1];
            }
            client.createPollService(name, question);
        } finally {
            client.shutdown();
        }
    }


}
