package com.coderscampus.dinaassignment13.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//This class with the annotations is helping Hibernate to create a table using SQL script
//ORM: object relational mapping which is Hibernate
//jdbc: java database connectivity

@Entity // Class name = User, DB table name = users
@Table(name = "users")
public class User {
	private Long userId;
	private String username;
	private String password;
	private String name;
	private LocalDate createdDate;
	private List<Account> accounts = new ArrayList<>();// many-to-many relationship: users <-> accounts
	private Address address; // object from one-to-one relationship

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically incrementing
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "user_account", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@OneToOne(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", createdDate=" + createdDate + ", accounts=" + accounts + ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
