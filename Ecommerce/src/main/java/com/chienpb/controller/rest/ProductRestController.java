package com.chienpb.controller.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.chienpb.model.Account;
import com.chienpb.model.ImpactLog;
import com.chienpb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chienpb.model.Product;
import com.chienpb.model.ProductCategory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/rest/products")
public class ProductRestController {
	@Autowired
	ProductService pService;
	@Autowired
	BrandService bService;
	@Autowired
	CategoryService cService;

	@Autowired
	UploadService uService;

	@Autowired
	SessionService session;

	@Autowired
	ImpactLogService iService;

	@GetMapping("")
	public Map<String, Object> get() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brands", bService.findAll());
		map.put("categories", cService.findAll());
		map.put("products", pService.findAll());
//		map.put("productCates", pService.findProductCategory());
		return map;
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<List<ProductCategory>> getProductDetail(@PathVariable("id") Long id) {
//		if (!pService.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		} else {
//			return ResponseEntity.ok(pService.findByProductId(id));
//		}
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductDetail(@PathVariable("id") Long id) {
		if (!pService.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(pService.findById(id));
		}
	}

	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);		
		if(keyword != null) {
			return pService.doSearch(keyword);
		}else {
			return pService.findAll();
		}
	}
	
	@PostMapping("")
	public Product postProduct(@RequestBody JsonNode data) {
		Account account = session.get("user");
		ImpactLog impactLog = new ImpactLog();
		impactLog.setUsername(account.getUsername());
		impactLog.setDescription("Thêm sản phẩm bởi " + account.getUsername());
		impactLog.setImpactTime(LocalDateTime.now());
		iService.save(impactLog);
		return pService.save(data);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> putProduct(@PathVariable("id") Long id, @RequestBody JsonNode data) {
		if (!pService.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Cập nhật sản phẩm bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok(pService.save(data));
		}
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
			Product product = pService.findById(id);
			TypeReference<List<String>> typeString = new TypeReference<List<String>>() {
			};
			ObjectMapper mapper = new ObjectMapper();
			try {
				pService.deleteById(id);
				Account account = session.get("user");
				ImpactLog impactLog = new ImpactLog();
				impactLog.setUsername(account.getUsername());
				impactLog.setDescription("Xóa sản phẩm bởi " + account.getUsername());
				impactLog.setImpactTime(LocalDateTime.now());
				iService.save(impactLog);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			
	}
	@DeleteMapping("/productcategory/{id}")
	public void deleteProductCategory(@PathVariable("id") Long id){
		pService.deleteProductCateById(id);
	}

	@PostMapping("/productcategory")
	public ProductCategory postProductCategory(@RequestBody ProductCategory productCates) {
		return pService.saveProductCates(productCates);
	}
}
