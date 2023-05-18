package com.chienpb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")
public class Role implements Serializable{

	@Id
	private String role;

	private String description;

//	@JsonIgnore
//	@OneToMany(mappedBy = "role")
//	private List<RoleDetail> roleDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Account> accounts;
}
