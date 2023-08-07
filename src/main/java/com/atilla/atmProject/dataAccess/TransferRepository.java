package com.atilla.atmProject.dataAccess;

import com.atilla.atmProject.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByUserId(Long userId);
}
