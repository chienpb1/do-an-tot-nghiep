package com.chienpb.service.IMPL;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chienpb.model.Account;
import com.chienpb.model.Role;
import com.chienpb.dao.AccountRepo;
import com.chienpb.dao.RoleRepo;
import com.chienpb.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired AccountRepo aRepo;
	@Autowired RoleRepo rRepo;
//	@Autowired RoleDetailRepo rdRepo;

	@Override
	public List<Account> findAll() {
		return aRepo.getAll();
	}

	@Override
	public Account findByUsername(String username) {
		return aRepo.findByUsername(username);
	}

	@Override
	public Account save(Account account) {
		return aRepo.save(account);
	}

	@Override
	public void deleteByUsername(String username) {
		aRepo.deleteById(username);
	}

	@Override
	public boolean existsById(String username) {
		Account account = null;
		account = aRepo.findByUsername(username);
		return account != null;
	}

	@Override
	public boolean existsByEmail(String email) {
		Account account = null;
		account = aRepo.findByEmail(email);
		return account != null;
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		Account account = null;
		account = aRepo.findByPhoneNumber(phoneNumber);
		return account != null;
	}

	@Override
	public List<Account> findByFullname(String fullname) {
		return aRepo.findByFullname(fullname);
	}

	@Override
	public List<Role> findAllRole() {
		return rRepo.findAll();
	}

//	@Override
//	public List<RoleDetail> findAllAuthorities() {
//		return rdRepo.findAll();
//	}

//	@Override
//	public RoleDetail saveRoleDetail(RoleDetail authority) {
//		return rdRepo.save(authority);
//	}

//	@Override
//	public void deleteRoleDetail(Long id) {
//		rdRepo.deleteById(id);
//	}

	@Override
	public List<Account> findByUsernameLike(String username) {
		return aRepo.findByUsernameLike(username);
	}

	@Override
	public Long countCustomer(String role) {
		return aRepo.countCustomer(role);
	}

	@Override
	public String generatePassword() {
		String password = "";
		int minUppercaseCharacter = 1;
		int minLowercaseCharacter = 1;
		int minNumberCharacter = 1;
		int minSpecialCharacter = 1;
		int passwordMinLength = 8;


		Random random = new Random();
		password += random.ints(65, 90)
				.limit(minUppercaseCharacter)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		password += random.ints(97, 122)
				.limit(minLowercaseCharacter)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		password += random.ints(48, 57)
				.limit(minNumberCharacter)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		password += random.ints(35, 39)
				.limit(minSpecialCharacter)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		if(passwordMinLength - password.length() > 0) {
			password += random.ints(97, 122)
					.limit(passwordMinLength - password.length())
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
		}

		return password;
	}

}
