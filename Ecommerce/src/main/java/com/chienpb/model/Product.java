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

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String name;

	private Double price;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String description;

	private Integer unitsInStock;

	private Integer sold;

	private Integer whiteList;


	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	private Boolean available;

	private String images;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	Brand brand;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn(name = "category_id")
	Category category;

//	@JsonIgnore
//	@OneToMany(mappedBy = "product")
//	List<ProductCategory> productCategories;
	
}
