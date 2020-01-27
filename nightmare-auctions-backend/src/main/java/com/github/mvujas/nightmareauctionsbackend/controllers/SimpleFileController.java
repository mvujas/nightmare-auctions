package com.github.mvujas.nightmareauctionsbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.mvujas.nightmareauctionsbackend.managers.files.FileUploadManager;

@RestController
public class SimpleFileController {

	@Autowired
	private FileUploadManager fileUploadManager;
	
	@PostMapping("file")
	public String nesto(@RequestParam("file") MultipartFile file) {
		String filename = fileUploadManager.saveFile(file);
		System.out.println(filename);
		return filename;
	}
}
