package com.atilla.atmProject.business.responses;

import com.atilla.atmProject.entities.Withdraw;
import lombok.Data;

@Data
public class WithdrawResponse {
    Long id;
    String name;
    Long amount;
    Long userId;
    String userName;
    Long balance;
    Long newBalance;


    public WithdrawResponse(Withdraw withdraw){
        this.id = withdraw.getId();
        this.name = withdraw.getName();
        this.amount = withdraw.getAmount();
        this.userId = withdraw.getUser().getId();
        this.userName = withdraw.getUser().getName();
        this.balance = withdraw.getUser().getBalance();
        this.newBalance = withdraw.getNewBalance();

    }
}
