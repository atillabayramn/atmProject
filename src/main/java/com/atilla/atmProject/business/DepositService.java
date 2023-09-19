package com.atilla.atmProject.business;

import com.atilla.atmProject.business.requests.DepositCreateRequest;
import com.atilla.atmProject.business.responses.DepositResponse;
import com.atilla.atmProject.dataAccess.DepositRepository;
import com.atilla.atmProject.dataAccess.UserRepository;
import com.atilla.atmProject.entities.Deposit;
import com.atilla.atmProject.entities.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositService {

    private DepositRepository depositRepository;
    private UserRepository userRepository;
    private UserService userService;

    public DepositService(DepositRepository depositRepository, UserRepository userRepository, UserService userService) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<DepositResponse> getAllDeposits(Optional<Long> userId) {
        List<Deposit> list;
        if(userId.isPresent()) {
            list = depositRepository.findByUserId(userId.get());
        } else {
            list = depositRepository.findAll();
        }
        return list.stream().map(d -> new DepositResponse(d)).collect(Collectors.toList());
    }

    public Deposit depositOperation(DepositCreateRequest depositCreateRequest) {

        Optional<User> user = userRepository.findById(depositCreateRequest.getUserId());
        if(user.isPresent()){
            User tempUser = user.get();
                depositCreateRequest.setNewBalance(tempUser.getBalance() + depositCreateRequest.getAmount());
                tempUser.setBalance(tempUser.getBalance() + depositCreateRequest.getAmount());
                userService.updateOneUser(depositCreateRequest.getUserId(), tempUser);
                return createOneDeposit(depositCreateRequest);
        }else {
            return null;
        }
    }

    public Deposit createOneDeposit(DepositCreateRequest depositCreateRequest){
        Deposit deposit = new Deposit();
        Optional<User> user = userRepository.findById(depositCreateRequest.getUserId());
        if(user.isPresent()) {
            deposit.setName(depositCreateRequest.getName());
            deposit.setAmount(depositCreateRequest.getAmount());
            deposit.setNewBalance(depositCreateRequest.getNewBalance());
            deposit.setUser(user.get());
            deposit.setCreateDate(new Date());
            depositRepository.save(deposit);
            return deposit;
        }
        return null;
    }

    public Deposit getOneDeposit(Long depositId) {
        return depositRepository.findById(depositId).orElse(null);
    }
}
