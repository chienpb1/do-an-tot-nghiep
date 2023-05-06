package com.chienpb.service.IMPL;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.chienpb.dao.ProductRepo;
import com.chienpb.dto.OrderDTO;
import com.chienpb.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chienpb.model.Order;
import com.chienpb.model.OrderDetail;
import com.chienpb.dao.OrderDetailRepo;
import com.chienpb.dao.OrderRepo;
import com.chienpb.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired OrderRepo oRepo;
	@Autowired OrderDetailRepo odRepo;

	@Autowired
	ProductRepo productRepo;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		order.setCreateDate(LocalDateTime.now());
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());

		double total = 0.0;
		for(OrderDetail orderDetail : details) {
			total += orderDetail.getPrice() * orderDetail.getQuantity();
			Optional<Product> optional = productRepo.findById(orderDetail.getProduct().getId());
			Product product = optional.orElse(null);
			if(product != null){
				product.setSold(orderDetail.getQuantity());
				product.setUnitsInStock(product.getUnitsInStock() - orderDetail.getQuantity());
				productRepo.save(product);
			}
		}
		order.setTotal(total);
		oRepo.save(order);
		odRepo.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		return oRepo.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return oRepo.findByUsername(username);
	}

	@Override
	public List<Order> findOrderInMonth(Integer month){
		return oRepo.findOrderInMonth(month);
	}

	@Override
	public Integer countOrderInMonth(Integer month) {
		return oRepo.countOrderInMonth(month);
	}

	@Override
	public List<OrderDTO> getAll() {
		return oRepo.findAllOrder();
	}

	@Override
	public List<OrderDTO> findByDate(String date) {
		try {
			List<OrderDTO> allOrder = oRepo.findAllOrder();
			List<OrderDTO> listResult = new ArrayList<>();
			for(OrderDTO dto : allOrder){
				if(dto.getCreateDate().toString().equals(date)){
					listResult.add(dto);
				}
			}
//			Date dateFromString = null;
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//			dateFromString = format.parse(date);
//			Date date1 = new Date(dateFromString.getYear(), dateFromString.getMonth(), dateFromString.getDay());
//			System.out.println(date1);
			return listResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oRepo.findAllOrder();
	}


}
