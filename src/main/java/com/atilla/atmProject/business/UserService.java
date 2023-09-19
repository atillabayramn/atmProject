package com.atilla.atmProject.business;

import com.atilla.atmProject.dataAccess.DepositRepository;
import com.atilla.atmProject.dataAccess.TransferRepository;
import com.atilla.atmProject.dataAccess.UserRepository;
import com.atilla.atmProject.dataAccess.WithdrawRepository;
import com.atilla.atmProject.entities.Deposit;
import com.atilla.atmProject.entities.Transfer;
import com.atilla.atmProject.entities.User;
import com.atilla.atmProject.entities.Withdraw;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    UserRepository userRepository;
    WithdrawRepository withdrawRepository;
    DepositRepository depositRepository;
    TransferRepository transferRepository;

    public UserService(UserRepository userRepository, WithdrawRepository withdrawRepository,
                       DepositRepository depositRepository, TransferRepository transferRepository) {
        this.userRepository = userRepository;
        this.withdrawRepository = withdrawRepository;
        this.depositRepository = depositRepository;
        this.transferRepository = transferRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setBalance(newUser.getBalance());
            foundUser.setUserId(newUser.getUserId());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }


    public List<Object> getUserSummary(Long userId) {
        List<Withdraw> withdraws = withdrawRepository.findWithdrawByUserId(userId);
        List<Deposit> deposits = depositRepository.findDepositByUserId(userId);
        List<Transfer> transfers = transferRepository.findTransferByUserId(userId);

        List<Object> result = new ArrayList<>();
        result.addAll(withdraws);
        result.addAll(deposits);
        result.addAll(transfers);

        return result;
    }
}
