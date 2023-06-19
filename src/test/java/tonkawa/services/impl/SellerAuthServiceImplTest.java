package tonkawa.services.productservices.impl;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tonkawa.dao.SellerDao;
import tonkawa.entities.Seller;
import tonkawa.entities.dto.SellerDto;
import tonkawa.payload.request.SignupRequest;
import tonkawa.services.MailService;
import tonkawa.services.impl.SellerAuthServiceImpl;
import tonkawa.services.impl.SignupResult;
import tonkawa.utils.BarcodeGeneratorExecuter;
import tonkawa.utils.BarcodeGeneratorStrategy;
import tonkawa.utils.ObjectToJsonConverterImpl;
import tonkawa.utils.ZxingBarcodeGeneratorImpl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SellerAuthServiceImplTest {
    @Mock
    private SellerDao sellerDao;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private MailService mailService;

    @Mock
    private ObjectToJsonConverterImpl objectToJsonConverter;

    @InjectMocks
    private SellerAuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSignup() throws IOException, MessagingException {
        // Arrange
        SignupRequest signupRequest = new SignupRequest("username", "email", "password");
        Seller user = new Seller("username", "email", "encodedPassword");


        when(sellerDao.existsByEmail(signupRequest.getEmail())).thenReturn(false);
        when(encoder.encode(signupRequest.getPassword())).thenReturn("encodedPassword");
        when(objectToJsonConverter.convert(any(SellerDto.class))).thenReturn("signupData");


        // Act
        SignupResult<Seller> result = authService.signup(signupRequest);

        // Assert
        assertNotNull(result);
        assertNull(result.getMessageResponse());
        assertEquals(user, result.getUser());

        verify(sellerDao).existsByEmail(signupRequest.getEmail());
        verify(sellerDao).save(user);
        verify(encoder).encode(signupRequest.getPassword());
        verify(objectToJsonConverter).convert(any(SellerDto.class));
    }

}
