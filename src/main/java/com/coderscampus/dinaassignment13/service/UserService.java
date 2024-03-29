package com.coderscampus.dinaassignment13.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.dinaassignment13.domain.Account;
import com.coderscampus.dinaassignment13.domain.User;
import com.coderscampus.dinaassignment13.repository.AccountRepository;
import com.coderscampus.dinaassignment13.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accountRepo;

	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public List<User> findByNameAndUsername(String name, String username) {
		return userRepo.findByNameAndUsername(name, username);
	}

	public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
		return userRepo.findByCreatedDateBetween(date1, date2);
	}

	public User findExactlyOneUserByUsername(String username) {
		List<User> users = userRepo.findExactlyOneUserByUsername(username);
		if (users.size() > 0)
			return users.get(0);
		else
			return new User();
	}

	public Set<User> findAll() {
		return userRepo.findAllUsersWithAccountsAndAddresses();
	}

	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}

	public User saveUser(User user) {
		if (user.getUserId() == null) {

			Account account = new Account();
			account.getUsers().add(user);

			user.getAccounts().add(account);

			accountRepo.save(account);
		}

		return userRepo.save(user);

	}

	public User findUserByIdWithAccounts(Long userId) {
		Optional<User> userO = userRepo.findUserByIdAndTheirAccounts(userId);
		return userO.orElse(new User());
	}

	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}

}
