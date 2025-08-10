package org.example.splitwise.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterUserResponseDto {
    private long userId;
    private ResponseStatus responseStatus;
    private String message;
}
