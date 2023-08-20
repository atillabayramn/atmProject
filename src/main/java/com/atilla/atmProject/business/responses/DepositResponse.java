package com.atilla.atmProject.business.responses;

import com.atilla.atmProject.entities.Deposit;
import lombok.Data;

@Data
public class DepositResponse {
    Long id;
    String name;
    Long amount;
    Long userId;
    String userName;
    Long balance;
    Long newBalance;


    public DepositResponse(Deposit deposit){
        this.id = deposit.getId();
        this.name = deposit.getName();
        this.amount = deposit.getAmount();
        this.userId = deposit.getUser().getId();
        this.userName = deposit.getUser().getName();
        this.balance = deposit.getUser().getBalance();
        this.newBalance = deposit.getNewBalance();

    }
}
