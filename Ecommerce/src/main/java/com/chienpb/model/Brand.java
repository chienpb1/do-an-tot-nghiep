package com.chienpb.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "Brands")
public class Brand implements Serializable{
	@Id
	private String id;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String name;

	private String image;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	private LocalDateTime updateDate;

	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private List<Product> products;
}
