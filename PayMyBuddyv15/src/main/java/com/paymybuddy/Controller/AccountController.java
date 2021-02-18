package com.paymybuddy.Controller;

import com.paymybuddy.Model.Account;
import com.paymybuddy.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/email")
    public Account findByOwner_Email(@RequestParam String adresse) {
        return accountService.findAccountByEmail(adresse);
    }
}

