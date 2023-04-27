package com.chienpb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chienpb.model.Product;
import com.chienpb.service.ProductService;

@RestController
@CrossOrigin("*")
public class CartRestController {
	@Autowired ProductService pService;
	@RequestMapping("/rest/products/{id}")
	public Product getOne(@PathVariable("id") Long id) {
		return pService.findById(id);
	}
}
