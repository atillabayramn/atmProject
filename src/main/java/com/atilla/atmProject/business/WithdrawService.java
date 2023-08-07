package com.atilla.atmProject.business;

import com.atilla.atmProject.business.requests.WithdrawCreateRequest;
import com.atilla.atmProject.dataAccess.UserRepository;
import com.atilla.atmProject.dataAccess.WithdrawRepository;
import com.atilla.atmProject.entities.User;
import com.atilla.atmProject.entities.Withdraw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawService {
    private WithdrawRepository withdrawRepository;
    private UserRepository userRepository;
    private UserService userService;


    public WithdrawService(WithdrawRepository withdrawRepository, UserRepository userRepository, UserService userService) {
        this.withdrawRepository = withdrawRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public List<Withdraw> getAllWithdraws(Optional<Long> userId) {
        if(userId.isPresent())
            return withdrawRepository.findByUserId(userId.get());
        return withdrawRepository.findAll();
    }

    public Withdraw withdrawOperation(WithdrawCreateRequest newWithdrawRequest) {
        Optional<User> user = userRepository.findById(newWithdrawRequest.getUserId());
        if(user.isPresent()){
            User tempUser = user.get();
            if(tempUser.getBalance() >= newWithdrawRequest.getAmount()){
                newWithdrawRequest.setNewBalance(tempUser.getBalance() - newWithdrawRequest.getAmount());
                tempUser.setBalance(tempUser.getBalance() - newWithdrawRequest.getAmount());
                userService.updateOneUser(newWithdrawRequest.getUserId(), tempUser);
                return createOneWithdraw(newWithdrawRequest);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public Withdraw createOneWithdraw(WithdrawCreateRequest withdrawCreateRequest){
        Withdraw withdraw = new Withdraw();
        Optional<User> user = userRepository.findById(withdrawCreateRequest.getUserId());
        if(user.isPresent()) {
            withdraw.setName(withdrawCreateRequest.getName());
            withdraw.setAmount(withdrawCreateRequest.getAmount());
            withdraw.setNewBalance(withdrawCreateRequest.getNewBalance());
            withdraw.setUser(user.get());
            withdrawRepository.save(withdraw);
            return withdraw;
        }
        return null;
    }

    public Withdraw getOneWithdraw(Long withdrawId) {
        return withdrawRepository.findById(withdrawId).orElse(null);
    }

}
