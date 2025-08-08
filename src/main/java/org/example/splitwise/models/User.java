package org.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String password;
    @ManyToMany(mappedBy = "members")
    private List<Group> groups;

    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus;
}
