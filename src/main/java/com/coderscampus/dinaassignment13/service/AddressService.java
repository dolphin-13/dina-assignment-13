package com.coderscampus.dinaassignment13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.dinaassignment13.domain.Address;
import com.coderscampus.dinaassignment13.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepo;

	public Address findAddressByUserId(Long userId) {
		return addressRepo.getById(userId);
	}

	public Address saveAddress(Address address) {

		return addressRepo.save(address);
	}
}
