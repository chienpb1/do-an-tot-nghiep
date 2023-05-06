package com.chienpb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{

	@Id
	private String username;

	private String password;

	private String fullname;

	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String photo;

	private Boolean activated;

	@Temporal(TemporalType.DATE)
	@Column(name = "createdate")
	private Date createDate = new Date();

	private Date updateDate;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<RoleDetail> roleDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
}
