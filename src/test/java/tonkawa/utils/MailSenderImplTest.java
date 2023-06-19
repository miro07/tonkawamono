package tonkawa.utils;


import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailSenderImpl mailSender;

    @Test
    public void sendEmail() throws MessagingException, IOException {
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Text";
        MimeMessage mimeMessage=Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailSender.sendEmail(to, subject, text);

        verify(javaMailSender).send(mimeMessage);
    }
    @Test
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage mimeMessage=Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailSender.sendEmailWithAttachment(anyString(), anyString(), anyString(), any(File.class), anyString());

        verify(javaMailSender).send(mimeMessage);
    }
    @Test
    public void sendEmailWithInlineDataSource() throws MessagingException, IOException {
        // Arrange
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Text";
        boolean isHtmlText = true;
        ByteArrayDataSource dataSource = new ByteArrayDataSource("Hello, World!".getBytes(), "text/plain");
        String dataSourceName = "data.txt";
        MimeMessage mimeMessage=Mockito.mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailSender.sendEmailWithInlineDataSource(anyString(), anyString(), anyString(), anyBoolean(), any(ByteArrayDataSource.class), anyString());

        verify(javaMailSender).send(mimeMessage);
    }

}
