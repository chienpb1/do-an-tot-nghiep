package com.chienpb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chienpb.model.Brand;


@Repository
public interface BrandRepo extends JpaRepository<Brand, String>{
	@Query("SELECT b FROM Brand b WHERE b.name LIKE :name")
	List<Brand> findByName(@Param("name") String name);

	@Query(value = "SELECT b FROM Brand b WHERE b.available is true order by coalesce(b.updateDate, b.createDate) desc")
	List<Brand> getAllBrand();

	@Query(value = "SELECT b FROM Brand b WHERE b.available is true order by coalesce(b.updateDate, b.createDate) desc")
	List<Brand> getAllBrandAndKhac();

}
