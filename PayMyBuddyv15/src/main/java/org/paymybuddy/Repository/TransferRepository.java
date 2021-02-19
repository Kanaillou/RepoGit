package org.paymybuddy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.paymybuddy.Model.Transfers;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfers,Long> {
}