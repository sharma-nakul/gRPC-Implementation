# gRPC-Implementation
#### Problem Statement ###

# Purpose
The goal of this lab is to understand how to design and implement a RPC API while learning new gRPC framework.
Steps

Converts this below REST API to a gRPC API. Here is the gRPC-java binding documentation to start.
Install Protobuf 3.0.0 and Protobuf Java binding
Download protobuf-java-3.0.0-alpha-2.tar.gz (or zip) from https://github.com/google/protobuf/releases and unzip via “tar xvf xxxxx.tar.gz”
Run these commands...

cd protobuf-3.0.0-alpha-2/
./autogen.sh
./configure
make
make check # ignore if you get 1 failure
sudo make install
cd java
mvn install

# check protoc installation
protoc --version # You will get this output => libprotoc 3.0.0
git clone https://github.com/grpc/grpc-java
cd grpc-java/
Change from io.netty:netty-codec-http2:4.1.0.Beta5-SNAPSHOT to io.netty:netty-codec-http2:4.1.0.Beta4 in build.gradle file
./gradlew install

### Run the server ###
./gradlew :grpc-examples:helloWorldServer

### Run the client in another terminal window and you will receive a message
./gradlew :grpc-examples:helloWorldClient

This tutorial shows you how to define a service (CreatePollService) in proto file.
Create a Poll REST API
Resource: /moderators/{moderator_id}/polls
Description: Create a new poll
Request:
POST /moderators/12345/polls (with the following payload in the request body)
HTTP Headers:
Content-type: application/json
{
"question": "What type of smartphone do you have?",
"started_at": "2015-02-23T13:00:00.000Z",
"expired_at" : "2015-02-24T13:00:00.000Z",
"choice": [ "Android", "iPhone" ]
}
Response:
HTTP Code: 201
{
"id" : "1ADC2FZ"   # Convert long/int to Base 36 for readability
}
ProtoBuf Interface Definition

syntax = "proto3";
package edu.sjsu.cmpe273.lab2;
option java_multiple_files = true;
option java_package = "edu.sjsu.cmpe273.lab2";
option java_outer_classname = "PollServiceProto";
// The poll service definition.
service PollService {
 // creates a poll
 rpc CreatePoll (PollRequest) returns (PollResponse) {}
}
message PollRequest {
 string moderatorId = 1;
 string question = 2;
 string startedAt = 3;
 string expiredAt = 4;
 repeated string choice = 5;
}
// The response message containing the new poll id
message PollResponse {
 string id = 1;
}
