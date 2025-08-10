package org.example.splitwise.repositories;

import org.example.splitwise.models.Expense;
import org.example.splitwise.models.Group;
import org.example.splitwise.services.expense.ExpenseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
