package org.example.splitwise.commands;

import org.example.splitwise.SplitwiseApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ExitCommand implements Command {

    @Override
    public boolean matches(String input) {
        List<String> words = Arrays.stream(input.split(" ")).toList();
        return words.getFirst().equalsIgnoreCase(CommandKeywords.exitCommand);
    }

    @Override
    public void execute(String input) {
        System.out.println("Exiting....!");
        System.out.println("Have a Good day!");
        SplitwiseApplication.running = false;
    }
}
