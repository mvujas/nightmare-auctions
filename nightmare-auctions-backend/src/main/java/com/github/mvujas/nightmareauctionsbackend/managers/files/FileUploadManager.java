package com.github.mvujas.nightmareauctionsbackend.managers.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.utility.RandomString;

@Component
public class FileUploadManager {
	
	@Autowired
	private UploadFolderNameHolder uploadFolderNameHolder;
	
	public String saveFile(MultipartFile file) {
		String originalFileName = file.getOriginalFilename();
		FileNameContainer fileNameContainer = new FileNameContainer(originalFileName);
		
		String randomName = generateRandomFileName();
		
		Path fileLocation;
		int counter = 0;
		do {
			fileNameContainer.setName(randomName + counter);
			counter++;
			fileLocation = uploadFolderNameHolder
					.getFileUploadFolderPath()
					.resolve(fileNameContainer.getFullName());
		} while(Files.exists(fileLocation));
		
        try {
			Files.copy(
					file.getInputStream(), 
					fileLocation, 
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileNameContainer.getFullName();
	}
	
	public static String generateRandomFileName() {
		return RandomString.make(20);
	}

	private static class FileNameContainer {
		private String name;
		private String nameExtension;
		
		public FileNameContainer(String fullFilename) {
			if(fullFilename == null) {
				throw new NullPointerException("Filename cannot be null");
			}
			
			int lastDotIndex = fullFilename.lastIndexOf('.');
			if(lastDotIndex == -1) {
				throw new RuntimeException("Unsupported filename");
			}
			
			name = fullFilename.substring(0, lastDotIndex);
			nameExtension = fullFilename.substring(lastDotIndex + 1);
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNameExtension() {
			return nameExtension;
		}
		public void setNameExtension(String nameExtension) {
			this.nameExtension = nameExtension;
		}
		
		public String getFullName() {
			return String.format(
					"%s.%s", 
					name,
					nameExtension);
		}
	}
	
}
