package com.atilla.atmProject.business;

import com.atilla.atmProject.business.requests.TransferCreateRequest;
import com.atilla.atmProject.business.responses.TransferResponse;
import com.atilla.atmProject.dataAccess.TransferRepository;
import com.atilla.atmProject.dataAccess.UserRepository;
import com.atilla.atmProject.entities.Transfer;
import com.atilla.atmProject.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private TransferRepository transferRepository;
    private UserRepository userRepository;
    private UserService userService;

    public TransferService(TransferRepository transferRepository, UserRepository userRepository, UserService userService) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<TransferResponse> getAllTransfers(Optional<Long> userId) {
        List<Transfer> list;
        if(userId.isPresent()) {
            list = transferRepository.findByUserId(userId.get());
        } else {
            list = transferRepository.findAll();
        }
        return list.stream().map(t -> new TransferResponse(t)).collect(Collectors.toList());
    }

    public Transfer transferOperation(TransferCreateRequest transferCreateRequest) {

        Optional<User> sendMoneyUser = userRepository.findById(transferCreateRequest.getUserId());
        Optional<User> getMoneyUser = userRepository.findById(transferCreateRequest.getGetMoneyUserId());

        if(sendMoneyUser.isPresent() && getMoneyUser.isPresent()){
            User givenUser = sendMoneyUser.get();
            User takenUser = getMoneyUser.get();

            givenUser.setBalance(givenUser.getBalance() - transferCreateRequest.getAmount());
            takenUser.setBalance(takenUser.getBalance() + transferCreateRequest.getAmount());
            userService.updateOneUser(transferCreateRequest.getUserId(), givenUser);
            userService.updateOneUser(transferCreateRequest.getGetMoneyUserId(), takenUser);
            return createOneTransfer(transferCreateRequest);
        } else {
            return null;
        }

    }

    public Transfer createOneTransfer(TransferCreateRequest transferCreateRequest){
        Transfer transfer = new Transfer();
        Optional<User> user = userRepository.findById(transferCreateRequest.getUserId());
        if(user.isPresent()) {
            transfer.setName(transferCreateRequest.getName());
            transfer.setAmount(transferCreateRequest.getAmount());
            transfer.setGetMoneyUserId(transferCreateRequest.getGetMoneyUserId());
            transfer.setUser(user.get());
            transferRepository.save(transfer);
            return transfer;
        }
        return null;
    }

    public Transfer getOneTransfer(Long transferId) {
        return transferRepository.findById(transferId).orElse(null);
    }
}
