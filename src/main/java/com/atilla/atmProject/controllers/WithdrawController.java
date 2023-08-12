package com.atilla.atmProject.controllers;

import com.atilla.atmProject.business.WithdrawService;
import com.atilla.atmProject.business.requests.WithdrawCreateRequest;
import com.atilla.atmProject.business.responses.WithdrawResponse;
import com.atilla.atmProject.entities.Withdraw;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/withdraws")
public class WithdrawController {

    private WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }


    @GetMapping
    public List<WithdrawResponse> getAllWithdraws(@RequestParam Optional<Long> userId){
        return withdrawService.getAllWithdraws(userId);
    }

    @PostMapping
    public Withdraw createOneWithdraw(@RequestBody WithdrawCreateRequest newWithdrawRequest){
        return withdrawService.withdrawOperation(newWithdrawRequest);
    }

    @GetMapping("/{Id}")
    public Withdraw getOneWithdraw(@PathVariable Long Id){
        return withdrawService.getOneWithdraw(Id);
    }




}
