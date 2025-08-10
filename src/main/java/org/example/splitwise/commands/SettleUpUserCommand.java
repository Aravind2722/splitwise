package org.example.splitwise.commands;

import org.example.splitwise.controllers.ExpenseController;
import org.example.splitwise.dtos.SettleUpUserRequestDto;
import org.example.splitwise.dtos.SettleUpUserResponseDto;
import org.example.splitwise.services.expense.ExpenseService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SettleUpUserCommand implements Command {
    private final ExpenseController expenseController;
    public SettleUpUserCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }
    @Override
    public boolean matches(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        return words.size() == 2 && words.getLast().equalsIgnoreCase(CommandKeywords.settleUpCommand);
    }

    @Override
    public void execute(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        SettleUpUserRequestDto requestDto = new SettleUpUserRequestDto();
        requestDto.setUserId(Long.parseLong(words.getFirst()));

        SettleUpUserResponseDto responseDto = expenseController.settleUpUser(requestDto);
        System.out.println(responseDto);
    }
}
