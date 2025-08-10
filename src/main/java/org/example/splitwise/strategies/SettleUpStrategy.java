package org.example.splitwise.strategies;

import org.example.splitwise.models.Expense;
import org.example.splitwise.services.expense.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<Expense> expenses);
}
