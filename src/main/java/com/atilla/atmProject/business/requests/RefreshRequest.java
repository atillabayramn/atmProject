package com.atilla.atmProject.business.requests;

import lombok.Data;

@Data
public class RefreshRequest {
    Long userId;
    String refreshToken;
}
