package com.github.mvujas.nightmareauctionsbackend.managers.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadFolderNameHolder {

	@Value("${files.uploadFolder}")
	private String fileUploadFolderPathName;
	
	private Path fileUploadFolderPath = null;
	
	private Pattern pattern = Pattern.compile("^/\\w:/");
	
	public Path getFileUploadFolderPath() {
		if(fileUploadFolderPath == null) {
			String resourcePath = getClass().getResource("/").getPath();
			if(pattern.matcher(resourcePath).find()) {
				resourcePath = resourcePath.substring(1);
			}
			Path source = Paths.get(resourcePath);
			fileUploadFolderPath = Paths.get(
					source.toAbsolutePath() + fileUploadFolderPathName);
			System.out.println(fileUploadFolderPath);
		}
		return fileUploadFolderPath;
	}

	public String getFileUploadFolderPathName() {
		return fileUploadFolderPathName;
	}
	
	
	
}
