package controllers;

import entities.Seller;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.request.SignupRequest;
import services.impl.SellerAuthServiceImpl;
import services.impl.SignupResult;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    SellerAuthServiceImpl authService;

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) throws MessagingException, IOException {
        SignupResult<Seller> signupResult = authService.signup(signupRequest);
        if(signupResult.getUser() == null){
            return ResponseEntity
                    .badRequest()
                    .body(signupResult.getMessageResponse());
        }
        return ResponseEntity.ok(signupResult.getUser());
    }
}
