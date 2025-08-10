package org.example.splitwise.commands;

import org.example.splitwise.controllers.ExpenseController;
import org.example.splitwise.dtos.SettleUpGroupRequestDto;
import org.example.splitwise.dtos.SettleUpGroupResponseDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SettleUpGroupCommand implements Command {
    private final ExpenseController expenseController;

    public SettleUpGroupCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        return (words.size() == 3 && words.get(1).equalsIgnoreCase(CommandKeywords.settleUpCommand));
    }

    @Override
    public void execute(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        SettleUpGroupRequestDto requestDto = new SettleUpGroupRequestDto();
        requestDto.setGroupId(Long.parseLong(words.getLast()));

        SettleUpGroupResponseDto responseDto = expenseController.settleUpGroup(requestDto);
        System.out.println(responseDto);
    }
}
