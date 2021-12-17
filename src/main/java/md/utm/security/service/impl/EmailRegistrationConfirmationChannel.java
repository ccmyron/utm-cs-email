package md.utm.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.utm.security.config.EmailProperties;
import md.utm.security.model.User;
import md.utm.security.service.RegistrationConfirmationChannel;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@Primary
@RequiredArgsConstructor
@Slf4j
public class EmailRegistrationConfirmationChannel implements RegistrationConfirmationChannel {

    private final JavaMailSender emailSender;
    private final EmailProperties emailProperties;

    @Override
    public void sendConfirmationMessage(User user, String registrationToken) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(emailProperties.getSender());

            messageHelper.setSubject("Confirm your Account");
            messageHelper.setTo(user.getEmail());
            messageHelper.setText(buildConfirmationMessageBody(user.getName(), registrationToken), true);

            emailSender.send(message);
            log.info("Sent a confirmation email to '{}'", user.getEmail());
        } catch (Exception ex) {
            log.warn("Could not send confirmation email", ex);
        }
    }

    private static String buildConfirmationMessageBody(String userName, String confirmationToken) {
        return "Hi " + userName + ", please confirm your account by following this " +
                "<a href='" + buildConfirmationUrl(confirmationToken) + "'>link</a>.";
    }

    private static String buildConfirmationUrl(String confirmationToken) {
        return "http://localhost:8080/register/confirm?confirmation-token=" + confirmationToken;
    }
}
