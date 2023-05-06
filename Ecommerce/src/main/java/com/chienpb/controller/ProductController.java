package com.chienpb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chienpb.model.Product;
import com.chienpb.model.ProductCategory;
import com.chienpb.service.BrandService;
import com.chienpb.service.CategoryService;
import com.chienpb.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired BrandService bService;

	@Autowired CategoryService cService;
	@Autowired ProductService pService;
	
	@RequestMapping("/list")
	public String home(Model model,
			@RequestParam("kw") Optional<String> kw,
			@RequestParam("cid") Optional<String> cid,
			@RequestParam("bid") Optional<String> bid, 
			@RequestParam("p") Optional<Integer> p) {
			if(cid.isPresent()) {
				Page<Product> lstProduct = pService.findProductByCategory(cid, p);
				List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
				model.addAttribute("page", lstProduct);
				model.addAttribute("products", products);
			}else if(bid.isPresent()) {
				Page<Product> lstProduct = pService.findProductByBrand(bid, p);
				List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
				model.addAttribute("page", lstProduct);
				model.addAttribute("products", products);
			}else {
				Page<Product> lstProduct = pService.searchProductByName(kw, p);
				List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
				model.addAttribute("page", lstProduct);
				model.addAttribute("products", products);
			}
		return "product/list";
	}
	@GetMapping("/list/brand")
	public String filterByListBrand(Model model,
			@RequestParam("bid") List<String> bid,
			@RequestParam("p") Optional<Integer> p) {
		
		Page<Product> lstProduct = pService.findProductByListBrand(bid, p);
		List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
		model.addAttribute("page", lstProduct);
		model.addAttribute("products", products);
		return "product/list";
	}
	@GetMapping("/list/price/{price}")
	public String filterByPrice(Model model,
			@PathVariable("price") String price,
			@RequestParam("p") Optional<Integer> p) {
		if(price.equalsIgnoreCase("under100")) {
			Page<Product> lstProduct = pService.findProductLessThanPrice(100000.0, p);
			List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
			model.addAttribute("page",lstProduct);
			model.addAttribute("products", products);
		}
		else if(price.equalsIgnoreCase("100-300")) {
			Page<Product> lstProduct = pService.findProductBetweenPrice(100000.0, 300000.0, p);
			List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
			model.addAttribute("page",lstProduct);
			model.addAttribute("products", products);
		}
		else if(price.equalsIgnoreCase("300-900")) {
			Page<Product> lstProduct = pService.findProductBetweenPrice(300000.0, 900000.0, p);
			List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
			model.addAttribute("page",lstProduct);
			model.addAttribute("products", products);
		}
		else if(price.equalsIgnoreCase("over900")) {
			Page<Product> lstProduct = pService.findByPriceGreaterThanEqual(900000.0, p);
			List<Map<String, Object>> products = pService.listProductSearch(lstProduct);
			model.addAttribute("page",lstProduct);
			model.addAttribute("products", products);
		}
		return "product/list";
	}
	@RequestMapping("/detail/{id}")
	public String detail(Model model,
			@PathVariable("id") Long id) {
		Map<String, Object> map = pService.ProductDetail(id);
		model.addAttribute("product", map);
		
		Product product = (Product) map.get("product");
		List<ProductCategory> productCates = new ArrayList<>();

		List<String> categories = new ArrayList<String>();
		categories.add(product.getCategory().getId());
		System.out.println(product.getCategory().getId() + " iD");
//		for (ProductCategory productCate : productCates){
//			categories.add(productCate.getCategory().getId());
//		}
		Optional<Integer> p = Optional.of(0);
		Page<Product> pageProduct = pService.findProductByListCategory(categories, p);
		List<Map<String, Object>> products = pService.listProductSearch(pageProduct);
		model.addAttribute("productsRcm", products);
		return "product/product-detail";
	}
}
