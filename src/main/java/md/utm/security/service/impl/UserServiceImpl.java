package md.utm.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.utm.security.model.User;
import md.utm.security.service.RegistrationConfirmationChannel;
import md.utm.security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final RegistrationConfirmationChannel confirmationChannel;

    private final List<User> users = new ArrayList<>();
    private final HashMap<String, User> registrationTokenRegistry = new HashMap<>();

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void registerUser(String name, String email) {
        // Create the user
        User user = new User(name, email);
        users.add(user);

        // Require additional account confirmation using an outbound channel
        String registrationToken = buildRegistrationToken();
        registrationTokenRegistry.put(registrationToken, user);
        confirmationChannel.sendConfirmationMessage(user, registrationToken);
    }

    @Override
    public void confirmUserRegistration(String registrationToken) {
        User user = registrationTokenRegistry.get(registrationToken);
        if (user == null) {
            throw new IllegalArgumentException("Registration token is invalid or it have expired");
        }

        user.confirm();
        registrationTokenRegistry.remove(registrationToken);
    }

    private String buildRegistrationToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
