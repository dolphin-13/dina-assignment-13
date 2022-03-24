package com.coderscampus.dinaassignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.dinaassignment13.domain.Account;
import com.coderscampus.dinaassignment13.domain.User;
import com.coderscampus.dinaassignment13.service.AccountService;
import com.coderscampus.dinaassignment13.service.UserService;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@PostMapping("/users/{userId}/accounts")
	public String createAccount(@PathVariable Long userId) {
		User user = userService.findUserByIdWithAccounts(userId);
		Account account = accountService.createNewAccount(user);
		userService.saveUser(user);

		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}

	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getOneAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		Account account = accountService.findAccountById(accountId);
		User user = userService.findUserByIdWithAccounts(userId);
		model.put("user", user);
		model.put("account", account);
		return "account";
	}

	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postOneAccount(@PathVariable Long userId, Account account) {
		User user = userService.findUserByIdWithAccounts(userId);
		user = userService.saveUser(user);
		account = accountService.saveAccount(account);
		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}
}
