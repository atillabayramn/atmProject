package com.atilla.atmProject.business.responses;

import com.atilla.atmProject.entities.Deposit;
import com.atilla.atmProject.entities.Transfer;
import lombok.Data;

@Data
public class TransferResponse {

    Long id;
    String name;
    Long amount;
    Long userId;
    String userName;
    Long balance;
    Long getMoneyUserId;


    public TransferResponse(Transfer transfer){
        this.id = transfer.getId();
        this.name = transfer.getName();
        this.amount = transfer.getAmount();
        this.userId = transfer.getUser().getId();
        this.userName = transfer.getUser().getUsername();
        this.balance = transfer.getUser().getBalance();
        this.getMoneyUserId = transfer.getGetMoneyUserId();

    }
}
