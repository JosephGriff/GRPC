package ie.gmit.ds;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;


public class PasswordServiceImpl extends PasswordServiceGrpc.PasswordServiceImplBase {
    // Logger
    private static final Logger logger = Logger.getLogger(PasswordServiceImpl.class.getName());

    public PasswordServiceImpl() {
    }

    public void hash(PasswordHashRequest request, StreamObserver<PasswordHashResponse> responseObserver) {
        try {
            char[] password = request.getPassword().toCharArray();
            byte[] salt = Passwords.getNextSalt();
            byte[] hashedPw = Passwords.hash(password, salt);

            ByteString hashedPw1 = ByteString.copyFrom(hashedPw);
            ByteString salt1 = ByteString.copyFrom(salt);

            responseObserver.onNext(PasswordHashResponse.newBuilder().setUserId(request.getUserId())
                    .setHashedPassword(hashedPw1).setSalt(salt1).build());
            // Displays on the server when a new password is created/added.
            logger.info("Added New Password " + request);

        } catch (RuntimeException exception) {
            responseObserver.onNext(PasswordHashResponse.newBuilder().getDefaultInstanceForType());
        }
        responseObserver.onCompleted();
    }

    public void validate(PasswordValidateRequest request, StreamObserver<BoolValue> responseObserver) {
        try {
            char[] password = request.getPassword().toCharArray();
            byte[] salt = Passwords.getNextSalt();
            byte[] expectedHash = Passwords.hash(password, salt);

            // Validate the hash matches and return true
            if (Passwords.isExpectedPassword(password, salt, expectedHash)) {
                responseObserver.onNext(BoolValue.newBuilder().setValue(true).build());
            }
            // Else return false
            else {
                responseObserver.onNext(BoolValue.newBuilder().setValue(true).build());
            }
        } catch (StatusRuntimeException exception1) {
            logger.log(Level.WARNING, "GRPC FAILED: (0)", exception1.getStatus());
            return;
        }
    }

} // PasswordServiceImpl