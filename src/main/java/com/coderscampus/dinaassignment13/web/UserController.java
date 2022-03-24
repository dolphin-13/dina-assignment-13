package com.coderscampus.dinaassignment13.web;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.dinaassignment13.domain.Address;
import com.coderscampus.dinaassignment13.domain.User;
import com.coderscampus.dinaassignment13.service.AddressService;
import com.coderscampus.dinaassignment13.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@GetMapping("/register")
	public String getCreateUser(ModelMap model) {
		model.put("user", new User());
		return "register";
	}

	// For "post" mapping we need to have an object (User) and return "redirect"
	// ModelMap model won't work if we do redirect
	@PostMapping("/register")
	public String postCreateUser(User user) {
		System.out.println(user);
		userService.saveUser(user);
		return "redirect:/register";
	}

	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		Set<User> users = userService.findAll();
		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}
		return "users"; // users.html
	}

	@GetMapping("/users/{userId}")
	public String getOneUser(ModelMap model, @PathVariable Long userId) {
		User user = userService.findUserByIdWithAccounts(userId);
		model.put("users", Arrays.asList(user));
		model.put("user", user);

		if (user.getAddress() == null) {
			Address address = new Address();
			model.put("address", address);
		} else {
			Address userWithAddress = addressService.findAddressByUserId(user.getUserId());
			model.put("address", userWithAddress);
		}

		return "users";
	}

	@PostMapping("/users/{userId}") // "/users/{userId}" is in href of users.html
	public String postOneUser(@PathVariable Long userId, User user) {

		User userWithAccounts = userService.findUserByIdWithAccounts(userId);
		user.setAccounts(userWithAccounts.getAccounts());

		user.getAddress().setUserId(userId);
		user.getAddress().setUser(user);

		if (user.getAddress().getUserId() == null)
			user.getAddress().setUserId(userId);

		userService.saveUser(user);

		return "redirect:/users/" + user.getUserId();
	}

	@PostMapping("/users/{userId}/delete") // "/users/{userId}/delete" is in action of users.html
	public String deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}

}
