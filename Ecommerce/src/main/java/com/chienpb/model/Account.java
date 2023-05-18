package com.chienpb.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{

	@Id
	private String username;

	private String password;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String fullname;

	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String photo;

	private Boolean activated;

//	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

//	@JsonIgnore
//	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
//	private List<RoleDetail> roleDetails;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
}
