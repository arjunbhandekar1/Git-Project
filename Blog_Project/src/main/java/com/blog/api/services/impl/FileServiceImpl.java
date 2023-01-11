package com.blog.api.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.services.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// fileName
		String name = file.getOriginalFilename();

		// fullpath
		String filePath = path + File.separator + name;

		// createFolder if not created...
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		// fileCopy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		//
		// this.fileRepo.save(name);

		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		// db logic to retun inputStream:
		
		
		return is;

	}

}
