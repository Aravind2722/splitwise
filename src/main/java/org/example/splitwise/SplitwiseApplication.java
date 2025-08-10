package org.example.splitwise;

import org.example.splitwise.commands.CommandRegistry;
import org.example.splitwise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
    private Scanner scanner;
    public static boolean running = true;
    private CommandRegistry commandRegistry;

    @Autowired
    public SplitwiseApplication(
            CommandRegistry commandRegistry
    ) {
        this.scanner = new Scanner(System.in);
        this.commandRegistry = commandRegistry;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Splitwise!");
        while (running) {
            System.out.println("Please enter your command: ");
            String input = scanner.nextLine();
            commandRegistry.execute(input);
        }
    }
}
