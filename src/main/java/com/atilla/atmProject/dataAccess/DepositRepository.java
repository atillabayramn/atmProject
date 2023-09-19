package com.atilla.atmProject.dataAccess;

import com.atilla.atmProject.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByUserId(Long userId);

    @Query(value = "select * from deposits where user_id = :userId order by create_date desc",
            nativeQuery = true)
    List<Deposit> findDepositByUserId(@Param("userId") Long userId);
}
