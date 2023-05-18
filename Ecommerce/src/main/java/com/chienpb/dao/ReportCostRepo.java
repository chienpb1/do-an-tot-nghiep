package com.chienpb.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chienpb.report.ReportCost;

@Repository
public interface ReportCostRepo extends JpaRepository<ReportCost, LocalDateTime>{
	@Query("SELECT new com.chienpb.report.ReportCost(o.createDate, COUNT(DISTINCT o.id), SUM(od.quantity * od.price) ) "
			+ "FROM Order o, OrderDetail od "
			+ "WHERE o.status = 2 AND o.id = od.order.id AND MONTH(o.createDate) = :month "
			+ "GROUP BY o.createDate")
	List<ReportCost> reportCost(@Param("month") Integer month);
}
