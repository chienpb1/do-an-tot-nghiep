package com.chienpb.controller.rest;

import com.chienpb.dto.OrderDTO;
import com.chienpb.dto.OrderDetailDTO;
import com.chienpb.model.*;
import com.chienpb.service.ImpactLogService;
import com.chienpb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chienpb.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class OrderRestController {
	@Autowired OrderService oService;
	@Autowired
	SessionService session;
	@Autowired
	ImpactLogService iService;

	@PostMapping("/rest/order")
	public Integer create(@RequestBody JsonNode order) {
		Order result = oService.create(order);
		if(result == null){
			return 0;
		}
		return 1;
	}

	@GetMapping("/rest/order/get-all")
	public List<OrderDTO> getAll() {
		System.out.println(oService.getAll() + " ORDERS");
		return oService.getAll();
	}

	@GetMapping("/rest/order/get-detail/{id}")
	public OrderDetailDTO getOrderDetail(@PathVariable("id") Integer id) {
		return oService.getOrderDetailByOrderId(id);
	}

	@PutMapping("/rest/order/confirm-order/{id}")
	public void confirmOrder(@PathVariable("id") Long id) {
		oService.confirmOrder(id);
		Account account = session.get("user");
		ImpactLog impactLog = new ImpactLog();
		impactLog.setUsername(account.getUsername());
		impactLog.setDescription("Xác nhận đơn hàng # "+id+" bởi " + account.getUsername());
		impactLog.setImpactTime(LocalDateTime.now());
		iService.save(impactLog);
	}

	@PutMapping("/rest/order/delete-order/{id}")
	public void cancelOrder(@PathVariable("id") Long id) {
		oService.cancelOrder(id);
		Account account = session.get("user");
		ImpactLog impactLog = new ImpactLog();
		impactLog.setUsername(account.getUsername());
		impactLog.setDescription("Hủy đơn hàng # "+id+" bởi " + account.getUsername());
		impactLog.setImpactTime(LocalDateTime.now());
		iService.save(impactLog);
	}

	@GetMapping("/rest/order/search")
	public List<OrderDTO> searchOrder(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);
		System.out.println(keyword + " KEY");
		if(!Objects.equals(keyword, "")) {
			return oService.findByDate(keyword);
		}else {
			return this.getAll();
		}
	}
}
