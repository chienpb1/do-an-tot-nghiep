package com.chienpb.controller.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chienpb.model.Order;
import com.chienpb.model.OrderDetail;
import com.chienpb.report.ReportCost;
import com.chienpb.report.ReportProduct;
import com.chienpb.service.AccountService;
import com.chienpb.service.OrderService;
import com.chienpb.service.ReportService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/rest/report")
public class ReportRestController {
	@Autowired AccountService aService;
	@Autowired OrderService oService;
	@Autowired ReportService rpService;
	
	public Integer monthCurrent() {
		Date date = new Date();
		return date.getMonth()+1;
	}
	
	@GetMapping("/total")
	public Map<String, Object> total() {
		Integer month = this.monthCurrent();
		Map<String, Object> db = new HashMap<String, Object>();
		db.put("totalCustomer", aService.countCustomer("user"));
		
		List<Order> orders = oService.findOrderInMonth(month);
		Double totalCost = 0.0;
		for(Order order : orders ) {
			List<OrderDetail> orderDetail = order.getOrderDetails();
			for(OrderDetail od : orderDetail) {
				totalCost += od.getPrice() * od.getQuantity();
			}
		}
		db.put("totalCost", totalCost);
		db.put("totalOrder", oService.countOrderInMonth(month));
		return db;
	}
	@GetMapping("/reportcost")
	public List<ReportCost> reportCostInMonth(){
		List<ReportCost> lst = rpService.reportCostInMonth(this.monthCurrent());
		return lst;
	}
	@GetMapping("/bestSellerInMonth")
	public List<ReportProduct> reportProductInMonth(){
		List<ReportProduct> lst = rpService.reportProductInMonth(this.monthCurrent());
		return lst;
	}
}
