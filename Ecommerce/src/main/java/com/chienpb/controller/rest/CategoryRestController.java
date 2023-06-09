package com.chienpb.controller.rest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.chienpb.model.Account;
import com.chienpb.model.ImpactLog;
import com.chienpb.service.ImpactLogService;
import com.chienpb.service.SessionService;
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

import com.chienpb.model.Category;
import com.chienpb.service.CategoryService;
import com.chienpb.service.UploadService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService cService;
	@Autowired
	UploadService uService;
	@Autowired
	SessionService session;
	@Autowired
	ImpactLogService iService;

	@GetMapping("")
	public List<Category> getAllCategory() {
		return cService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") String id) {
		if (!cService.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(cService.findById(id));
		}
	}
	@GetMapping("/search")
	public List<Category> searchCategory(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);		
		if(keyword != null) {
			return cService.findByName("%"+keyword+"%");
		}else {
			return this.getAllCategory();
		}
	}
	@PostMapping("")
	public ResponseEntity<Category> postCategory(@RequestBody Category cate){
		if(cService.existsById(cate.getId())) {
			return ResponseEntity.badRequest().build();
		}else {
			cate.setUpdateDate(LocalDateTime.now());
			cate.setAvailable(true);
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Thêm danh mục sản phẩm bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok(cService.save(cate));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> putCategory(@PathVariable("id") String id, @RequestBody Category cate, HttpServletRequest request){
		System.out.println(request + " request");
		if(!cService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}else {
			cate.setUpdateDate(LocalDateTime.now());
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Cập nhật danh mục sản phẩm bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok(cService.save(cate));
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id){
		if(!cService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}else {
			cService.deleteById(id);
			Account account = session.get("user");
			ImpactLog impactLog = new ImpactLog();
			impactLog.setUsername(account.getUsername());
			impactLog.setDescription("Xóa danh mục sản phẩm bởi " + account.getUsername());
			impactLog.setImpactTime(LocalDateTime.now());
			iService.save(impactLog);
			return ResponseEntity.ok().build();
		}
	}
	
}

