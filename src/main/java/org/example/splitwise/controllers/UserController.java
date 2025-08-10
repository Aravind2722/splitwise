package org.example.splitwise.controllers;

import org.example.splitwise.dtos.RegisterUserRequestDto;
import org.example.splitwise.dtos.RegisterUserResponseDto;
import org.example.splitwise.dtos.ResponseStatus;
import org.example.splitwise.exceptions.UserAlreadyExistsException;
import org.example.splitwise.models.User;
import org.example.splitwise.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto) {
        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        try {
            User user = userService.registerUser(requestDto.getName(), requestDto.getPhoneNumber(), requestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setMessage("User registered successfully!");
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserAlreadyExistsException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        } catch (Exception e) {
            responseDto.setMessage("Something went wrong. Please try again later!");
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
