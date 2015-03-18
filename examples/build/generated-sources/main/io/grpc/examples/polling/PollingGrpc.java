package io.grpc.examples.polling;

import static io.grpc.stub.Calls.createMethodDescriptor;
import static io.grpc.stub.Calls.asyncUnaryCall;
import static io.grpc.stub.Calls.asyncServerStreamingCall;
import static io.grpc.stub.Calls.asyncClientStreamingCall;
import static io.grpc.stub.Calls.duplexStreamingCall;
import static io.grpc.stub.Calls.blockingUnaryCall;
import static io.grpc.stub.Calls.blockingServerStreamingCall;
import static io.grpc.stub.Calls.unaryFutureCall;
import static io.grpc.stub.ServerCalls.createMethodDefinition;
import static io.grpc.stub.ServerCalls.asyncUnaryRequestCall;
import static io.grpc.stub.ServerCalls.asyncStreamingRequestCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class PollingGrpc {

  private static final io.grpc.stub.Method<io.grpc.examples.polling.PollRequest,
      io.grpc.examples.polling.PollResponse> METHOD_GET_POLL =
      io.grpc.stub.Method.create(
          io.grpc.MethodType.UNARY, "getPoll",
          io.grpc.proto.ProtoUtils.marshaller(io.grpc.examples.polling.PollRequest.PARSER),
          io.grpc.proto.ProtoUtils.marshaller(io.grpc.examples.polling.PollResponse.PARSER));

  public static PollingStub newStub(io.grpc.Channel channel) {
    return new PollingStub(channel, CONFIG);
  }

  public static PollingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PollingBlockingStub(channel, CONFIG);
  }

  public static PollingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PollingFutureStub(channel, CONFIG);
  }

  public static final PollingServiceDescriptor CONFIG =
      new PollingServiceDescriptor();

  @javax.annotation.concurrent.Immutable
  public static class PollingServiceDescriptor extends
      io.grpc.stub.AbstractServiceDescriptor<PollingServiceDescriptor> {
    public final io.grpc.MethodDescriptor<io.grpc.examples.polling.PollRequest,
        io.grpc.examples.polling.PollResponse> getPoll;

    private PollingServiceDescriptor() {
      getPoll = createMethodDescriptor(
          "grpc.example.polling.Polling", METHOD_GET_POLL);
    }

    @SuppressWarnings("unchecked")
    private PollingServiceDescriptor(
        java.util.Map<java.lang.String, io.grpc.MethodDescriptor<?, ?>> methodMap) {
      getPoll = (io.grpc.MethodDescriptor<io.grpc.examples.polling.PollRequest,
          io.grpc.examples.polling.PollResponse>) methodMap.get(
          CONFIG.getPoll.getName());
    }

    @java.lang.Override
    protected PollingServiceDescriptor build(
        java.util.Map<java.lang.String, io.grpc.MethodDescriptor<?, ?>> methodMap) {
      return new PollingServiceDescriptor(methodMap);
    }

    @java.lang.Override
    public com.google.common.collect.ImmutableList<io.grpc.MethodDescriptor<?, ?>> methods() {
      return com.google.common.collect.ImmutableList.<io.grpc.MethodDescriptor<?, ?>>of(
          getPoll);
    }
  }

  public static interface Polling {

    public void getPoll(io.grpc.examples.polling.PollRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.polling.PollResponse> responseObserver);
  }

  public static interface PollingBlockingClient {

    public io.grpc.examples.polling.PollResponse getPoll(io.grpc.examples.polling.PollRequest request);
  }

  public static interface PollingFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.polling.PollResponse> getPoll(
        io.grpc.examples.polling.PollRequest request);
  }

  public static class PollingStub extends
      io.grpc.stub.AbstractStub<PollingStub, PollingServiceDescriptor>
      implements Polling {
    private PollingStub(io.grpc.Channel channel,
        PollingServiceDescriptor config) {
      super(channel, config);
    }

    @java.lang.Override
    protected PollingStub build(io.grpc.Channel channel,
        PollingServiceDescriptor config) {
      return new PollingStub(channel, config);
    }

    @java.lang.Override
    public void getPoll(io.grpc.examples.polling.PollRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.polling.PollResponse> responseObserver) {
      asyncUnaryCall(
          channel.newCall(config.getPoll), request, responseObserver);
    }
  }

  public static class PollingBlockingStub extends
      io.grpc.stub.AbstractStub<PollingBlockingStub, PollingServiceDescriptor>
      implements PollingBlockingClient {
    private PollingBlockingStub(io.grpc.Channel channel,
        PollingServiceDescriptor config) {
      super(channel, config);
    }

    @java.lang.Override
    protected PollingBlockingStub build(io.grpc.Channel channel,
        PollingServiceDescriptor config) {
      return new PollingBlockingStub(channel, config);
    }

    @java.lang.Override
    public io.grpc.examples.polling.PollResponse getPoll(io.grpc.examples.polling.PollRequest request) {
      return blockingUnaryCall(
          channel.newCall(config.getPoll), request);
    }
  }

  public static class PollingFutureStub extends
      io.grpc.stub.AbstractStub<PollingFutureStub, PollingServiceDescriptor>
      implements PollingFutureClient {
    private PollingFutureStub(io.grpc.Channel channel,
        PollingServiceDescriptor config) {
      super(channel, config);
    }

    @java.lang.Override
    protected PollingFutureStub build(io.grpc.Channel channel,
        PollingServiceDescriptor config) {
      return new PollingFutureStub(channel, config);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.polling.PollResponse> getPoll(
        io.grpc.examples.polling.PollRequest request) {
      return unaryFutureCall(
          channel.newCall(config.getPoll), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Polling serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("grpc.example.polling.Polling")
      .addMethod(createMethodDefinition(
          METHOD_GET_POLL,
          asyncUnaryRequestCall(
            new io.grpc.stub.ServerCalls.UnaryRequestMethod<
                io.grpc.examples.polling.PollRequest,
                io.grpc.examples.polling.PollResponse>() {
              @java.lang.Override
              public void invoke(
                  io.grpc.examples.polling.PollRequest request,
                  io.grpc.stub.StreamObserver<io.grpc.examples.polling.PollResponse> responseObserver) {
                serviceImpl.getPoll(request, responseObserver);
              }
            }))).build();
  }
}
