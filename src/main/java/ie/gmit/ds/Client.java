package ie.gmit.ds;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;
import com.google.protobuf.ByteString;
import com.google.protobuf.BoolValue;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.StatusRuntimeException;


public class Client {

    // Scanner for user input
    Scanner userIn = new Scanner(System.in);

    // Variables
    private int userId;
    private String userPassword;

    byte[] saltByte;
    ByteString salt;
    ByteString hashedPassword;
    boolean input = false;

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private final ManagedChannel channel;
    // **Generate sources in maven**
    private final PasswordServiceGrpc.PasswordServiceStub asyncPasswordService;
    private final PasswordServiceGrpc.PasswordServiceBlockingStub syncPasswordService;

    public Client(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        asyncPasswordService = PasswordServiceGrpc.newStub(channel);
        syncPasswordService = PasswordServiceGrpc.newBlockingStub(channel);

    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // User input to add password
    @SuppressWarnings("unused")
    public void addPassword() {

        System.out.println("Please Enter New User ID Num: ");
        userId = userIn.nextInt();


            System.out.println("\nPlease Enter User Password: ");
            userPassword = userIn.next();



        saltByte = Passwords.getNextSalt();
        salt = ByteString.copyFrom(saltByte);
        hashedPassword = ByteString.copyFrom(Passwords.hash(userPassword.toCharArray(), saltByte));

        PasswordHashRequest password = PasswordHashRequest.newBuilder().setUserId(userId).setPassword(userPassword).build();
        PasswordHashResponse pwResponse;

        try {
            System.out.println("Executing syncPasswordService");
            pwResponse = syncPasswordService.hash(password);
            System.out.println("Executing getHashedPassword");
            hashedPassword = pwResponse.getHashedPassword();
            System.out.println("Executing getSalt");
            salt = pwResponse.getSalt();
            System.out.println("End of Try");
        } catch (StatusRuntimeException exception) {
            System.out.println("In Catch");
            logger.log(Level.WARNING, "GRPC FAILED: (0)", exception.getStatus());
            return;
        }
    }

    //output to the user whether the password is valid or not
    public void validatePassword() {

        StreamObserver<BoolValue> responseObserver = new StreamObserver<BoolValue>() {
            @Override
            public void onNext(BoolValue value) {
                if (value.getValue())
                    System.out.println("The Password Entered is Valid !");
                else
                    System.out.println("The Password Entered is Invalid !");
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable t) {
            }

        };

        try {
            asyncPasswordService.validate(PasswordValidateRequest.newBuilder().setPassword(userPassword)
                    .setHashedPassword(hashedPassword).setSalt(salt).build(), responseObserver);
        } catch (StatusRuntimeException exception1) {
            logger.log(Level.WARNING, "GRPC FAILED: (0)", exception1.getStatus());
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 21213);
        try {
            client.addPassword();
            client.validatePassword();
        } finally {
            // Do not terminate for async response
            Thread.currentThread().join();
        }
    }

} // PasswordClient