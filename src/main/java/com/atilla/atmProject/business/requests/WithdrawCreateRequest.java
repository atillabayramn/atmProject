package com.atilla.atmProject.business.requests;

import lombok.Data;

@Data
public class WithdrawCreateRequest {
    Long id;
    String name;
    Long amount;
    Long newBalance;
    Long balance;
    Long userId;
}
