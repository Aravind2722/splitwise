package org.example.splitwise.services.expense;


import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.dtos.TransactionResponseDto;
import org.example.splitwise.models.User;

@Getter
@Setter
public class Transaction {
    private User from;
    private User to;
    private int amount;

}
