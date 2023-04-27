package com.chienpb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chienpb.model.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, String>{

}
