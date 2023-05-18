package com.chienpb.service;

import java.util.List;

import com.chienpb.model.Brand;

public interface BrandService {

	List<Brand> findAll();

	Brand findById(String id);

	boolean existsById(String id);

	Brand save(Brand brand);

	void deleteById(String id);

	List<Brand> findByName(String kw);

	List<Brand> findAllBrandAndKhac();
	
}
