package services;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import payload.request.SignupRequest;
import services.impl.SignupResult;

import java.io.IOException;

@Service
public interface AuthService<T> {

    SignupResult<T> signup(SignupRequest signupRequest) throws IOException, MessagingException;
}
