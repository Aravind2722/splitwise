package org.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDto {
    private List<TransactionResponseDto> transactions;
    private ResponseStatus responseStatus;
    private String message;
}
