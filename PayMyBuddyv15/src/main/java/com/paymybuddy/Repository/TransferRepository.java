package com.paymybuddy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paymybuddy.Model.Transfers;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfers,Long> {
}