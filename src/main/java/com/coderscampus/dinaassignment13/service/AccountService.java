package com.coderscampus.dinaassignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.dinaassignment13.domain.Account;
import com.coderscampus.dinaassignment13.domain.User;
import com.coderscampus.dinaassignment13.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}

	public Account createNewAccount(User user) {
		Account account = new Account();
		account.getUsers().add(user);
		user.getAccounts().add(account);
		account.setAccountName("Account #" + (user.getAccounts().size() - 1));
		return saveAccount(account);
	}

	public Account findAccountById(Long accountId) {
		Optional<Account> account = accountRepo.findById(accountId);
		return account.orElse(new Account());
	}

}
