package org.example.splitwise.strategies;


import org.antlr.v4.runtime.misc.Pair;
import org.example.splitwise.models.Expense;
import org.example.splitwise.models.User;
import org.example.splitwise.models.UserExpense;
import org.example.splitwise.models.UserExpenseType;
import org.example.splitwise.repositories.UserExpenseRepository;
import org.example.splitwise.services.expense.Transaction;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TwoBucketSettleUpStrategy implements SettleUpStrategy {

    private final UserExpenseRepository userExpenseRepository;

    public TwoBucketSettleUpStrategy(UserExpenseRepository userExpenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        // We have all the expenses for which we have to settle up...
        // Get all the USerExpenses that are involved in these expenses...
        // Calculate extra paid for each use involved in all these UserExpenses...
        // User Two bucket strategy to create transactions and return them...

        List<UserExpense> userExpensesInvolvingUserAndOthers = userExpenseRepository.findAllByExpenseIn(expenses);
        Map<User, Integer> extraPaid = new HashMap<>();

        for (UserExpense userExpense : userExpensesInvolvingUserAndOthers) {
            User user = userExpense.getUser();
            int amount = userExpense.getAmount();
            if (userExpense.getUserExpenseType().equals(UserExpenseType.HAD_TO_PAY)) amount = -amount;

            extraPaid.put(user, extraPaid.getOrDefault(user, 0)+amount);
        }

        Queue<Pair<User, Integer>> bucket1 = new LinkedList<>();
        Queue<Pair<User, Integer>> bucket2 = new LinkedList<>();

        for (Map.Entry<User, Integer> entry : extraPaid.entrySet()) {
            User user = entry.getKey();
            int amount = entry.getValue();
            if (amount == 0) continue;
            Pair<User, Integer> pair = new Pair<>(user, amount);
            if (amount < 0) bucket2.add(pair);
            else bucket1.add(pair);
        }

        List<Transaction> transactions = new ArrayList<>();
        while (!bucket1.isEmpty()) {
            Pair<User, Integer> pair1 = bucket1.remove();
            Pair<User, Integer> pair2 = bucket2.remove();
            int amount1 = (int)pair1.b;
            int amount2 = (int)pair2.b;
            Transaction transaction = new Transaction();

            transaction.setFrom((User) pair2.a);
            transaction.setTo((User) pair1.a);
            transaction.setAmount(Math.min(amount1, Math.abs(amount2)));

            if (amount1 < Math.abs(amount2)) bucket2.add(new Pair<>(pair2.a, amount2 + amount1));
            else if (amount1 > Math.abs(amount2)) bucket1.add(new Pair<>(pair1.a, amount1 - Math.abs(amount2)));

            transactions.add(transaction);
        }

        return transactions;
    }
}
