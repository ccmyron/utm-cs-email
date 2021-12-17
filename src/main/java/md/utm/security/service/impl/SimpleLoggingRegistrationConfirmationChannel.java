package md.utm.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import md.utm.security.model.User;
import md.utm.security.service.RegistrationConfirmationChannel;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleLoggingRegistrationConfirmationChannel implements RegistrationConfirmationChannel {

    @Override
    public void sendConfirmationMessage(User user, String registrationToken) {
        log.info("New confirmation request received: token='{}'", registrationToken);
    }
}
