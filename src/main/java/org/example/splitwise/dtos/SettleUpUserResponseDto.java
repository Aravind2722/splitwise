package org.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.services.expense.Transaction;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    private List<TransactionResponseDto> transactions;
    private ResponseStatus responseStatus;
    private String message;
}
