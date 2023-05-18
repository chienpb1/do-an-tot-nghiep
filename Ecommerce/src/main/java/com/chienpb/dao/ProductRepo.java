package com.chienpb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chienpb.model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	@Query("SELECT p FROM Product p WHERE p.available IS TRUE AND p.name LIKE :name")
	List<Product> findByName(@Param("name") String name);

	@Query("SELECT p FROM Product p WHERE p.available IS TRUE order by coalesce(p.updateDate, p.createDate) desc")
	List<Product> getAll();

	@Query(value = "SELECT p FROM Product p WHERE p.available IS TRUE AND p.name LIKE %?1% OR p.description LIKE %?1% order by coalesce(p.updateDate, p.createDate) desc")
	List<Product> doSearch(String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.available IS TRUE AND p.name LIKE :name")
	Page<Product> findByKeyword(@Param("name") String name, Pageable pageable);
	
//	@Query("SELECT DISTINCT p FROM Product p, ProductCategory pc WHERE p.id = pc.product.id AND pc.category.id = :cid AND p.name LIKE :name")
//	public Page<Product> findByCategory(@Param("name") String name, @Param("cid") String cid, Pageable pageable);
	
	@Query("SELECT DISTINCT p FROM Product p WHERE p.available IS TRUE AND p.category.id = :cid")
	public Page<Product> findByCategory(@Param("cid") String cid, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.available IS TRUE AND p.brand.id = :bid")
	public Page<Product> findByBrand(@Param("bid") String bid, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.available IS TRUE AND p.brand.id IN :bid")
	Page<Product> findByListBrand(@Param("bid") List<String> bid, Pageable pageable);
	
	Page<Product> findByPriceLessThanEqualAndAvailableIsTrue(Double price, Pageable pageable);
	
	Page<Product> findByPriceBetweenAndAvailableIsTrue(Double min, Double max, Pageable pageable);
	
	Page<Product> findByPriceGreaterThanEqualAndAvailableIsTrue(Double price, Pageable pageable);

	@Query("SELECT DISTINCT p FROM Product p WHERE p.available IS TRUE AND p.category.id IN :cid")
	Page<Product> findByListCategory(@Param("cid") List<String> cid, Pageable pageable);
	
	Optional<Product> findByIdAndAvailableIsTrue(Long id);

	@Query(value = "SELECT p FROM Product p WHERE p.available IS TRUE AND p.brand.id = ?1")
	List<Product> findByBrandId(String id);

}
