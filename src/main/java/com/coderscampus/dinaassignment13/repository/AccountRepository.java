package com.coderscampus.dinaassignment13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.dinaassignment13.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
