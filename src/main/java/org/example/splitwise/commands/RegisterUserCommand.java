package org.example.splitwise.commands;

import org.example.splitwise.controllers.UserController;
import org.example.splitwise.dtos.RegisterUserRequestDto;
import org.example.splitwise.dtos.RegisterUserResponseDto;
import org.example.splitwise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand implements Command {
    private UserController userController;
    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean matches(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        return words.size() == 4 && words.getFirst().equalsIgnoreCase(CommandKeywords.registerUserKeyword);
    }

    @Override
    public void execute(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setName(words.get(1));
        requestDto.setPhoneNumber(words.get(2));
        requestDto.setPassword(words.get(3));

        RegisterUserResponseDto responseDto = userController.registerUser(requestDto);
        System.out.println(responseDto);
    }
}
