package com.coderscampus.dinaassignment13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.dinaassignment13.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
