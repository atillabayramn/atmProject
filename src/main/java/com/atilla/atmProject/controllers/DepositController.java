package com.atilla.atmProject.controllers;

import com.atilla.atmProject.business.DepositService;
import com.atilla.atmProject.business.requests.DepositCreateRequest;
import com.atilla.atmProject.entities.Deposit;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deposits")
public class DepositController {

    private DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }


    @GetMapping
    public List<Deposit> getAllDeposits(@RequestParam Optional<Long> userId){
        return depositService.getAllDeposits(userId);
    }

    @PostMapping
    public Deposit createOneDeposit(@RequestBody DepositCreateRequest depositCreateRequest){
        return depositService.depositOperation(depositCreateRequest);
    }

    @GetMapping("/{Id}")
    public Deposit getOneDeposit(@PathVariable Long Id){
        return depositService.getOneDeposit(Id);
    }

}
