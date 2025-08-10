package org.example.splitwise.services.expense;

import org.example.splitwise.exceptions.GroupNotFoundException;
import org.example.splitwise.exceptions.UserNotFoundException;
import org.example.splitwise.models.Expense;
import org.example.splitwise.models.Group;
import org.example.splitwise.models.User;
import org.example.splitwise.models.UserExpense;
import org.example.splitwise.repositories.ExpenseRepository;
import org.example.splitwise.repositories.GroupRepository;
import org.example.splitwise.repositories.UserExpenseRepository;
import org.example.splitwise.repositories.UserRepository;
import org.example.splitwise.strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class ExpenseService {
    private final UserRepository userRepository;
    private final UserExpenseRepository userExpenseRepository;
    private final SettleUpStrategy settleUpStrategy;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(UserRepository userRepository,
                          UserExpenseRepository userExpenseRepository,
                          SettleUpStrategy settleUpStrategy,
                          GroupRepository groupRepository,
                          ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Transaction> settleUpUser(Long userId) throws UserNotFoundException {
        // Get all the UserExpenses where the current user is involved...
        // Get all the Expenses from all the UserExpenses. So that we can find other User'sExpenses too...
        // Use one of the SettleUpStrategies and return the transactions, by using these expenses...

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new UserNotFoundException("User "+userId+" not found!");

        List<UserExpense> userExpenses = userExpenseRepository.findAllByUser(userOptional.get());
        List<Expense> expensesInvolvingUser = userExpenses.stream().map(UserExpense::getExpense).toList();

        List<Transaction> transactions = settleUpStrategy.settleUp(expensesInvolvingUser);

        return transactions.stream().filter(transaction -> (transaction.getFrom().getId() == userId || transaction.getTo().getId() == userId)).toList();
    }

    public List<Transaction> settleUpGroup(Long groupId) throws GroupNotFoundException {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isEmpty()) throw new GroupNotFoundException("Group "+groupId+" not found!");
        List<Expense> groupExpenses = expenseRepository.findAllByGroup(groupOptional.get());

        return settleUpStrategy.settleUp(groupExpenses);
    }
}
