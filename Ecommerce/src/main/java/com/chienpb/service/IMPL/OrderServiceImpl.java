package com.chienpb.service.IMPL;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.chienpb.dao.AccountRepo;
import com.chienpb.dao.ProductRepo;
import com.chienpb.dto.OrderDTO;
import com.chienpb.dto.OrderDetailDTO;
import com.chienpb.dto.OrderProductDTO;
import com.chienpb.model.Account;
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

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepo oRepo;
	@Autowired
	OrderDetailRepo odRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	AccountRepo accountRepo;

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
			Optional<Product> optional = productRepo.findById(orderDetail.getProduct().getId());
			Product product = optional.orElse(null);
			if(product != null && product.getUnitsInStock() < orderDetail.getQuantity()){
				return null;
			}
		}
		for(OrderDetail orderDetail : details) {
			total += orderDetail.getPrice() * orderDetail.getQuantity();
			Optional<Product> optional = productRepo.findById(orderDetail.getProduct().getId());
			Product product = optional.orElse(null);
			if(product != null){
				product.setSold(product.getSold() + orderDetail.getQuantity());
				product.setUnitsInStock(product.getUnitsInStock() - orderDetail.getQuantity());
				productRepo.save(product);
			}
		}
		order.setStatus(1); // Mới đặt hàng, chờ xác nhận
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
	public Integer countOrderWaitingInMonth(Integer month) {
		return oRepo.countOrderWaitingInMonth(month);
	}

	@Override
	public Integer countOrderSuccessInMonth(Integer month) {
		return oRepo.countOrderSuccessInMonth(month);
	}

	@Override
	public Integer countOrderCancelInMonth(Integer month) {
		return oRepo.countOrderCancelInMonth(month);
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

	@Override
	public OrderDetailDTO getOrderDetailByOrderId(Integer id) {
		OrderDetailDTO dto = new OrderDetailDTO();
		List<OrderDetail> orderDetailList = odRepo.getOrderDetailByOrderId(Long.valueOf(id));
		OrderDetail orderDetail = orderDetailList.get(0);
		dto.setId(orderDetail.getOrder().getId());
		dto.setStatus(orderDetail.getOrder().getStatus());
		dto.setOrderDate(orderDetail.getOrder().getCreateDate());
		dto.setUsername(orderDetail.getOrder().getAccount().getUsername());
		dto.setFullname(orderDetail.getOrder().getAccount().getFullname());
		dto.setEmail(orderDetail.getOrder().getAccount().getEmail());
		dto.setPhoneNumber(orderDetail.getOrder().getAccount().getPhoneNumber());
		dto.setAddress(orderDetail.getOrder().getAddress());
		dto.setNote(orderDetail.getOrder().getNote());
		dto.setTotal(orderDetail.getOrder().getTotal());
		List<OrderProductDTO> dtos = new ArrayList<>();
		for(OrderDetail detail : orderDetailList){
			OrderProductDTO orderProductDTO = new OrderProductDTO();
			orderProductDTO.setProductName(detail.getProduct().getName());
			orderProductDTO.setQuantity(detail.getQuantity());
			orderProductDTO.setPrice(detail.getPrice());
			orderProductDTO.setAmount(detail.getPrice() * detail.getQuantity());
			orderProductDTO.setImg(detail.getProduct().getImages().replaceAll("\\[|\\]|\"", "").split(",")[0]);
			System.out.println(" IMAGE " + detail.getProduct().getImages());
			dtos.add(orderProductDTO);
		}
		dto.setOrderProduct(dtos);
		return dto;
	}

	@Transactional
	@Override
	public void confirmOrder(Long id) {
		oRepo.confirmOrder(id);
	}

	@Transactional
	@Override
	public void cancelOrder(Long id) {
		oRepo.cancelOrder(id);
		List<OrderDetail> orderDetails = odRepo.getOrderDetailByOrderId(id);
		for(OrderDetail orderDetail : orderDetails) {
			Optional<Product> optional = productRepo.findById(orderDetail.getProduct().getId());
			Product product = optional.orElse(null);
			if(product != null){
				product.setSold(product.getSold() - orderDetail.getQuantity());
				product.setUnitsInStock(product.getUnitsInStock() + orderDetail.getQuantity());
				productRepo.save(product);
			}
		}
	}


}
