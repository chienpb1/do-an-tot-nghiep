package com.chienpb.service;

import java.util.List;

import com.chienpb.dto.OrderDTO;
import com.chienpb.model.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode order);

	Order findById(Long id);

	List<Order> findByUsername(String username);

//	Double sumCostInMonth(Integer month);

	List<Order> findOrderInMonth(Integer month);

	Integer countOrderInMonth(Integer month);

	List<OrderDTO> getAll();

	List<OrderDTO> findByDate(String date);
	
}
