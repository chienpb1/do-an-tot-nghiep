package com.chienpb.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String address;

	private Double total;

	private Integer status;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String note;

	@ManyToOne
	@JoinColumn(name = "username")
	private Account account;

	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
}
