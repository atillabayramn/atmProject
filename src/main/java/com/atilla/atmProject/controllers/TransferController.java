package com.atilla.atmProject.controllers;

import com.atilla.atmProject.business.TransferService;
import com.atilla.atmProject.business.requests.TransferCreateRequest;
import com.atilla.atmProject.business.responses.TransferResponse;
import com.atilla.atmProject.entities.Transfer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public List<TransferResponse> getAllTransfers(@RequestParam Optional<Long> userId){
        return transferService.getAllTransfers(userId);
    }

    @PostMapping
    public Transfer createOneTransfer(@RequestBody TransferCreateRequest transferCreateRequest){
        return transferService.transferOperation(transferCreateRequest);
    }

    @GetMapping("/{Id}")
    public Transfer getOneTransfer(@PathVariable Long Id){
        return transferService.getOneTransfer(Id);
    }
}
