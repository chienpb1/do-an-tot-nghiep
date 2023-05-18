package com.chienpb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chienpb.model.Account;


@Repository 
public interface AccountRepo extends JpaRepository<Account, String>{
	@Query("SELECT a FROM Account a WHERE a.fullname LIKE :name")
	List<Account> findByFullname(@Param("name") String fullname);
	@Query("SELECT a FROM Account a WHERE a.username LIKE :username")
	List<Account> findByUsernameLike(@Param("username") String username);
//	@Query("SELECT COUNT(a) FROM Account a, RoleDetail rd WHERE a.username = rd.account.username AND rd.role.role = :role")
//	Long countCustomer(@Param("role") String role);

	@Query("SELECT COUNT(a) FROM Account a WHERE a.activated IS TRUE AND a.role.role = :role")
	Long countCustomer(@Param("role") String role);

	@Query(value = "SELECT a FROM Account a WHERE a.activated IS TRUE ORDER BY coalesce(a.updateDate, a.createDate) desc")
	List<Account> getAll();

	@Query(value = "SELECT a FROM Account a WHERE a.activated IS TRUE AND a.username = ?1")
	Account findByUsername(String username);

	@Query(value = "SELECT a FROM Account a WHERE a.activated IS TRUE AND a.email = ?1")
	Account findByEmail(String email);

	@Query(value = "SELECT a FROM Account a WHERE a.activated IS TRUE AND a.phoneNumber = ?1")
	Account findByPhoneNumber(String phoneNumber);
}
