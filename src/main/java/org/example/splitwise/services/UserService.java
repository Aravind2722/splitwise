package org.example.splitwise.services;

import org.example.splitwise.exceptions.UserAlreadyExistsException;
import org.example.splitwise.models.User;
import org.example.splitwise.models.UserStatus;
import org.example.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String phoneNumber, String password) throws UserAlreadyExistsException{
        // check if user with same phone number already present
        // if present:
        //          - check if user status is active. It means user has already been registered. So throw exception
        // If present and status is invited (or) if not present:
        //          - Create/ update user object with new values and save in db
        //          - return the updated/saved user
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        User user;
        if (userOptional.isPresent()) user = userOptional.get();
        else user = new User();

        UserStatus userStatus = user.getUserStatus();
        if (userStatus != null && userStatus.equals(UserStatus.ACTIVE)) throw new UserAlreadyExistsException("User with phone number "+phoneNumber+" already exists!");

        user.setUserStatus(UserStatus.ACTIVE);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        return userRepository.save(user);
    }
}
