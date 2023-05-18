package com.chienpb.controller.rest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.chienpb.model.Account;
import com.chienpb.model.ImpactLog;
import com.chienpb.model.Product;
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

import com.chienpb.model.Brand;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/rest/brands")
public class BrandRestController {
	@Autowired
	BrandService bService;
	@Autowired
	UploadService uService;
	@Autowired
	SessionService session;
	@Autowired
	ImpactLogService iService;
	@Autowired
	ProductService pService;

	@GetMapping("")
	public List<Brand> getAllBrand() {
		return bService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Brand> getBrand(@PathVariable("id") String id) {
		if (!bService.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(bService.findById(id));
		}
	}
	@GetMapping("/search")
	public List<Brand> searchBrand(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);		
		if(keyword != null) {
			return bService.findByName("%"+keyword+"%");
		}else {
			return bService.findAllBrandAndKhac();
		}
	}
	@PostMapping("")
	public ResponseEntity<Brand> postBrand(@RequestBody Brand brand){
		if(bService.existsById(brand.getId())) {
			return ResponseEntity.badRequest().build();
		}else {
			brand.setAvailable(Boolean.TRUE);
			brand.setCreateDate(LocalDateTime.now());
			bService.save(brand);
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Thêm thương hiệu bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok(brand);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Brand> putBrand(@PathVariable("id") String id, @RequestBody Brand brand){
		if(!bService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}else {
			brand.setUpdateDate(LocalDateTime.now());
			bService.save(brand);
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Cập nhật thương hiệu bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok(brand);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBrand(@PathVariable("id") String id){
		if(!bService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}else {
			Brand brand = bService.findById(id);
			Brand brandKhac = bService.findById("KHAC");
			brand.setAvailable(false);
			bService.save(brand);
			List<Product> productsByBrand = pService.findByBrandId(id);
			for (Product product : productsByBrand){
				product.setBrand(brandKhac);
				pService.save(product);
			}
//			String filename = brand.getImage();
//			if(!filename.equalsIgnoreCase("logo.jpg")) {
//				uService.delete("brand", filename);
//			}
//			bService.deleteById(id);
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Xóa thương hiệu bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok().build();
		}
	}
	
}

