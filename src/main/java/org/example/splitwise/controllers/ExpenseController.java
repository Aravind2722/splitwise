package org.example.splitwise.controllers;


import org.antlr.v4.runtime.atn.SemanticContext;
import org.example.splitwise.dtos.*;
import org.example.splitwise.exceptions.GroupNotFoundException;
import org.example.splitwise.exceptions.UserNotFoundException;
import org.example.splitwise.services.expense.ExpenseService;
import org.example.splitwise.services.expense.Transaction;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto requestDto) {
        SettleUpUserResponseDto responseDto = new SettleUpUserResponseDto();
        try {
            List<Transaction> transactions = expenseService.settleUpUser(requestDto.getUserId());
            responseDto.setTransactions(transactions.stream().map(transaction -> {
                TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
                transactionResponseDto.setFromId(transaction.getFrom().getId());
                transactionResponseDto.setToId(transaction.getTo().getId());
                transactionResponseDto.setFromName(transaction.getFrom().getName());
                transactionResponseDto.setToName(transaction.getTo().getName());
                transactionResponseDto.setAmount(transaction.getAmount());
                return transactionResponseDto;
            }).toList());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Transactions generated successfully!");
        } catch (UserNotFoundException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        } catch (Exception e) {
            responseDto.setMessage("Something went wrong. Please try again later!");
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto requestDto) {
        SettleUpGroupResponseDto responseDto = new SettleUpGroupResponseDto();
        try {
            List<Transaction> transactions = expenseService.settleUpGroup(requestDto.getGroupId());
            responseDto.setTransactions(transactions.stream().map(transaction -> {
                TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
                transactionResponseDto.setFromId(transaction.getFrom().getId());
                transactionResponseDto.setToId(transaction.getTo().getId());
                transactionResponseDto.setFromName(transaction.getFrom().getName());
                transactionResponseDto.setToName(transaction.getTo().getName());
                transactionResponseDto.setAmount(transaction.getAmount());
                return transactionResponseDto;
            }).toList());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Transactions generated successfully!");
        } catch (GroupNotFoundException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        } catch (Exception e) {
            responseDto.setMessage("Something went wrong. Please try again later!");
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
