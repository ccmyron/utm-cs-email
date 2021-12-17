package md.utm.security.service;

import md.utm.security.model.User;

public interface RegistrationConfirmationChannel {

    void sendConfirmationMessage(User user, String registrationToken);
}
