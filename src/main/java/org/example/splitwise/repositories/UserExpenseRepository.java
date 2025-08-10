package org.example.splitwise.repositories;

import org.example.splitwise.models.Expense;
import org.example.splitwise.models.User;
import org.example.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    List<UserExpense> findAllByUser(User user);
    List<UserExpense> findAllByExpenseIn(List<Expense> expenses);
}
