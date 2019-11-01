package ie.gmit.ds;

import java.io.IOException;
import java.util.logging.Logger;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class PasswordServer {
    /**  Adapted from Adapted from async lab:
     https://github.com/john-french/distributed-systems-labs/tree/master/grpc-async-inventory

        Run This Class before you run the PasswordClient.java
     */



    // Variables
    private Server gRPCServer;
    private static final Logger logger = Logger.getLogger(PasswordServer.class.getName());
    private static final int PORT = 21213;

    // Start the server
    private void start() throws IOException {
        gRPCServer = ServerBuilder.forPort(PORT).addService(new PasswordServiceImpl()).build().start();
        logger.info("Server Listening on Port: " + PORT);
    }

    @SuppressWarnings("unused")
    private void stop() {
        if (gRPCServer != null) {
            gRPCServer.shutdown();
        }
    }
    /**
     * Awaiting termination on the main thread because the grpc library is using daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (gRPCServer != null) {
            gRPCServer.awaitTermination();
        }
    }
    /**
     * Allows the client to register a new password
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final PasswordServer pwServer = new PasswordServer();
        pwServer.start();
        pwServer.blockUntilShutdown();
    }

} // PasswordServer