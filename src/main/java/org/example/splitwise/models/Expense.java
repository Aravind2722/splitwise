package org.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private int amount;
    private String description;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Group group;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
}
