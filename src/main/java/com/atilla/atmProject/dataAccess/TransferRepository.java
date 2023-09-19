package com.atilla.atmProject.dataAccess;

import com.atilla.atmProject.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByUserId(Long userId);

    @Query(value = "select * from transfers where user_id = :userId order by create_date desc",
            nativeQuery = true)
    List<Transfer> findTransferByUserId(@Param("userId") Long userId);
}
