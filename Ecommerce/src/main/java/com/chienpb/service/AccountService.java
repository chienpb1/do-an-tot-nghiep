package com.chienpb.service;

import java.util.List;

import com.chienpb.model.Account;
import com.chienpb.model.Role;

public interface AccountService {

	List<Account> findAll();
	
	Account findByUsername(String username);
	
	Account save(Account account);
	
	void deleteByUsername(String username);

	boolean existsById(String username);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	List<Account> findByFullname(String fullname);

	List<Role> findAllRole();

//	List<RoleDetail> findAllAuthorities();

//	RoleDetail saveRoleDetail(RoleDetail authority);

//	void deleteRoleDetail(Long id);

	List<Account> findByUsernameLike(String username);

	Long countCustomer(String role);
	
	String generatePassword();
}
