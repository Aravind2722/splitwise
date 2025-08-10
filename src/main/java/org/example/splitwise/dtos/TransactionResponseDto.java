package org.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDto {
    private Long fromId;
    private String fromName;
    private Long toId;
    private String toName;
    private int amount;

}
