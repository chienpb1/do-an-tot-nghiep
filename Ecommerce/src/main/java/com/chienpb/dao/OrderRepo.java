package com.chienpb.dao;

import java.util.Date;
import java.util.List;

import com.chienpb.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chienpb.model.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.account.username = :name")
	List<Order> findByUsername(@Param("name") String username);
	
	@Query("SELECT o FROM Order o WHERE MONTH(o.createDate) = :month")
	List<Order> findOrderInMonth(@Param("month") Integer month);
	
	@Query("SELECT COUNT(o) FROM Order o WHERE MONTH(o.createDate) = :month")
	Integer countOrderInMonth(@Param("month") Integer month);

	@Query(value = "SELECT new com.chienpb.dto.OrderDTO(o.id, o.createDate, o.account.username, o.address, o.total) FROM Order o ORDER By o.id, o.createDate")
	List<OrderDTO> findAllOrder();

//	@Query(value = "SELECT new com.chienpb.dto.OrderDTO(o.id, o.createDate, o.account.username, o.address, o.total) FROM Order o WHERE o.createDate = ?1 ORDER By o.id, o.createDate")
//	List<OrderDTO> findByCreateDate(Date date);

	
	
//	@Query("SELECT DISTINCT new ReportCategory(c.id, c.name, SUM(od.quantity)) FROM Category c, Product p, ProductCategory pc, Order o, OrderDetail od "
//			+ "WHERE c.id = pc.category.id AND p.id = pc.product.id AND p.id = od.product.id AND o.id = od.order.id AND MONTH(o.createDate) = :month "
//			+ "GROUP BY c.id, c.name ORDER BY SUM(od.quantity) DESC")
//	List<ReportCategory> topCategoryInMonth(@Param("month") Integer month);

}
