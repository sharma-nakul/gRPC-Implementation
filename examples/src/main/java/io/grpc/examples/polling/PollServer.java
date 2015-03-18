

package io.grpc.examples.polling;

import io.grpc.ServerImpl;
import io.grpc.stub.StreamObserver;
import io.grpc.transport.netty.NettyServerBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A sample gRPC server that serve the RouteGuide (see route_guide.proto) service.
 */
public class PollServer {
    private static final Logger logger = Logger.getLogger(PollServer.class.getName());

    private final int port;
    private ServerImpl gRpcServer;


    public PollServer(int port) {
            this.port = port;
    }

    public void start() {
        gRpcServer = NettyServerBuilder.forPort(port)
                .addService(PollingGrpc.bindService(new PollingService()))
                .build().start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                PollServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() {
        if (gRpcServer != null) {
            gRpcServer.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        PollServer server = new PollServer(8080);
        server.start();
    }

    private static class PollingService implements PollingGrpc.Polling {
        @Override
        public void getPoll(PollRequest req, StreamObserver<PollResponse> responseObserver) {
            PollResponse reply = PollResponse
                                    .newBuilder()
                                    .setName(req.getName())
                                    .setQuestion(req.getQuestion()).build();
            responseObserver.onValue(reply);
            responseObserver.onCompleted();
        }


    }
}

