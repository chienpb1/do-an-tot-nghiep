package com.chienpb.service;

import java.util.List;

import com.chienpb.dto.OrderDTO;
import com.chienpb.dto.OrderDetailDTO;
import com.chienpb.model.Order;
import com.chienpb.model.OrderDetail;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode order);

	Order findById(Long id);

	List<Order> findByUsername(String username);

//	Double sumCostInMonth(Integer month);

	List<Order> findOrderInMonth(Integer month);

	Integer countOrderInMonth(Integer month);

	Integer countOrderWaitingInMonth(Integer month);
	Integer countOrderSuccessInMonth(Integer month);
	Integer countOrderCancelInMonth(Integer month);

	List<OrderDTO> getAll();

	List<OrderDTO> findByDate(String date);

	OrderDetailDTO getOrderDetailByOrderId(Integer id);

	void confirmOrder(Long id);

	void cancelOrder(Long id);
}
