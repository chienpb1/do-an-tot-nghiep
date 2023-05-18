package com.chienpb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chienpb.model.OrderDetail;

import java.util.List;


@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long>{

    @Query(value = "SELECT od FROM OrderDetail od WHERE od.order.id = ?1")
    List<OrderDetail> getOrderDetailByOrderId(Long id);
}
