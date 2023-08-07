package com.atilla.atmProject.business.requests;

import lombok.Data;

@Data
public class TransferCreateRequest {
    Long id;
    String name;
    Long amount;
    Long getMoneyUserId;
    Long userId;
}
