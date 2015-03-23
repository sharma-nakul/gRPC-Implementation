package edu.sjsu.cmpe273.lab2;

import com.google.common.util.concurrent.SettableFuture;

import io.grpc.ChannelImpl;
import edu.sjsu.cmpe273.lab2.PollServiceGrpc.PollServiceBlockingStub;
import edu.sjsu.cmpe273.lab2.PollServiceGrpc.PollServiceStub;
import io.grpc.stub.StreamObserver;
import io.grpc.transport.netty.NegotiationType;
import io.grpc.transport.netty.NettyChannelBuilder;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client code that makes gRPC calls to the server.
 */
public class PollClient {
    private static final Logger logger = Logger.getLogger(PollClient.class.getName());

    private final ChannelImpl channel;
    private final PollServiceBlockingStub blockingStub;
    private final PollServiceStub asyncStub;

    public PollClient(String host, int port) {
        channel = NettyChannelBuilder.forAddress(host, port)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        blockingStub = PollServiceGrpc.newBlockingStub(channel);
        asyncStub = PollServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTerminated(5, TimeUnit.SECONDS);
    }


    public void addPollService(String moderatorId, String question, String startedAt, String expiredAt, ArrayList<String> choice) {
	if (choice == null || choice.size() < 2) {
            new RuntimeException("choice must have two items in it");
        }
        try {
			logger.info("\n\nCreating a new poll for moderator " + moderatorId +"\n\n");	
            PollRequest request = PollRequest.newBuilder()
				             .setModeratorId(moderatorId)
					     .setQuestion(question)
				       	     .setStartedAt(startedAt)
				             .setExpiredAt(expiredAt)
					     .addAllChoice(choice)
					     .build();
            
			PollResponse response = blockingStub.createPoll(request);
		logger.info("\n\nCreated a new poll with id = "+response.getId());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return;
        }
    }



    public static void main(String[] args) throws Exception {
        PollClient client = new PollClient("localhost", 50051);
        try {
            String moderatorId = "1";
			String question = "What type of smartphone do you have?";
			String startedAt = "2015-03-18T13:00:00.000Z";
			String expiredAt = "2015-03-18T13:00:00.000Z";
	ArrayList<String> choice = new ArrayList<String> ();
        choice.add("Android");
        choice.add("iPhone"); 		
            client.addPollService(moderatorId, question, startedAt, expiredAt, choice);
        } finally {
            client.shutdown();
        }
    }


}
