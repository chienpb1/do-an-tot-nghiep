package com.chienpb.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	File save(MultipartFile file, String folder);
	void delete(String folder, String name);
	List<File> save(MultipartFile[] files, String folder);
}
