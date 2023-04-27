package com.chienpb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chienpb.model.Account;
import com.chienpb.model.Order;
import com.chienpb.model.OrderDetail;
import com.chienpb.service.AccountService;
import com.chienpb.service.BrandService;
import com.chienpb.service.CategoryService;
import com.chienpb.service.OrderService;
import com.chienpb.service.ProductService;
import com.chienpb.service.SessionService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired BrandService bService;

	@Autowired CategoryService cService;

	@Autowired ProductService pService;

	@Autowired AccountService aService;
	@Autowired OrderService oService;
	@Autowired SessionService session;
	
	@RequestMapping("/list")
	public String listOrder(Model model) {
		Account account = session.get("user");
		List<Order> orders = oService.findByUsername(account.getUsername());

		List<Map<String, Object>> db = new ArrayList<Map<String,Object>>();
		for(Order order : orders ) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<OrderDetail> orderDetail = order.getOrderDetails();
			Double total = (double) 0;
			for(OrderDetail od : orderDetail) {
				total += od.getPrice() * od.getQuantity();
			}
			map.put("order", order);
			map.put("total", total);
			db.add(map);
		}
		model.addAttribute("orders", db);
		return "order/list";
	}
	@RequestMapping("/cart")
	public String cart() {
		return "order/cart";
	}
	@RequestMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("userDetail", session.get("user"));
		return "order/checkout";
	}
	@RequestMapping("/detail/{id}")
	public String detail(Model model, 
			Principal principal,
			@PathVariable("id") Long id) {
		Order order = oService.findById(id);
		Account account = session.get("user");
		String acc = account.getUsername();
		if(!order.getAccount().getUsername().equals(acc)) {
			return "redirect:/login?message=Access%20Denied";
		}else {
			model.addAttribute("order", order);
			List<OrderDetail> orderDetail = order.getOrderDetails();
			Double total = (double) 0;
			for(OrderDetail od : orderDetail) {
				total += od.getPrice() * od.getQuantity();
			}
			model.addAttribute("total", total);
			return "order/detail";
			
		}
	}
}
