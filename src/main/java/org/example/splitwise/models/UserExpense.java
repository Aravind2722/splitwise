package org.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

@Entity
@Getter
@Setter
public class UserExpense extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    private int amount;

    @Enumerated(value = EnumType.STRING)
    private UserExpenseType userExpenseType;
}
