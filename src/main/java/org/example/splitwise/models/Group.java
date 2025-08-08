package org.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "groupz")
@Getter
@Setter
public class Group extends BaseModel {
    private String name;
    private String description;
    @ManyToOne
    private User admin;

    @ManyToMany
    private List<User> members;

}
