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
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    private String id;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String name;

    private Boolean available;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    //	@JsonIgnore
//	@OneToMany(mappedBy = "category")
//	private List<ProductCategory> ProductCategories;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
