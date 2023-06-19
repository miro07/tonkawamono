package tonkawa.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import tonkawa.entities.Seller;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class ObjectToJsonConverterImplTest {
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ObjectToJsonConverterImpl objectToJsonConverterImpl;

    @Test
    public void convert () throws JsonProcessingException {
        Seller sellerObject = new Seller("usernametest", "emailtest@test.com", "passwordtest");
        String expectedJson = "{\"username\":\"usernametest\",\"email\":\"emailtest@test.com\",\"password\":\"passwordtest\"}";
        String jsonResult = objectToJsonConverterImpl.convert(sellerObject);
        assertEquals(jsonResult, expectedJson);
    }

}
