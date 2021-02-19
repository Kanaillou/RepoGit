package org.paymybuddy.Repository;

import org.paymybuddy.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//voir dépendance ?
// import java.security.acl.Owner;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();

    Account findByOwner_Email(String email);
}
