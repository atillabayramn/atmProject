package com.atilla.atmProject.dataAccess;

import com.atilla.atmProject.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByUserId(Long userId);
}
