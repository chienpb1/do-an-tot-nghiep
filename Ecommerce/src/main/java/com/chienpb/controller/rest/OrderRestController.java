package com.chienpb.controller.rest;

import com.chienpb.dto.OrderDTO;
import com.chienpb.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chienpb.model.Order;
import com.chienpb.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class OrderRestController {
	@Autowired OrderService oService;

	@PostMapping("/rest/order")
	public Order create(@RequestBody JsonNode order) {
		return oService.create(order);
	}

	@GetMapping("/rest/order/get-all")
	public List<OrderDTO> getAll() {
		System.out.println(oService.getAll() + " ORDERS");
		return oService.getAll();
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
