package com.atilla.atmProject.dataAccess;

import com.atilla.atmProject.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    List<Withdraw> findByUserId(Long userId);

    @Query(value = "select * from withdraws where user_id = :userId order by create_date desc",
            nativeQuery = true)
    List<Withdraw> findWithdrawByUserId(@Param("userId") Long userId);
}
