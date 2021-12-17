package md.utm.security.service;

import md.utm.security.model.User;

import java.util.List;

public interface UserService {

    /**
     * Find all users that are registered
     *
     * @return Registered users
     */
    List<User> findAllUsers();

    /**
     * Register a new user. Newly created account will require a confirmation using an outbound
     * channel.
     *
     * @param name  Name of the new user
     * @param email Email of the new user
     */
    void registerUser(String name, String email);

    /**
     * Confirm user's registration.
     *
     * @param registrationToken Token sent using an outbound channel
     */
    void confirmUserRegistration(String registrationToken);
}
