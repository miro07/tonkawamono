package utils;

import jakarta.mail.MessagingException;

import javax.activation.DataSource;
import java.io.File;

public interface MailSender {

    void sendEmail(String to, String subject, String text) throws MessagingException;
    void sendEmailWithAttachment(String to, String subject, String text, File attachment, String attachmentName) throws MessagingException;

    default void sendEmailWithInlineDataSource(String to, String subject, String text, Boolean isHtmlText, DataSource dataSource, String dataSourceName) throws MessagingException {};
}
