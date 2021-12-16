package ca.sheridancollege.newbytex.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	void saveImageToServer(MultipartFile multipartFile, String key) throws IOException;

}
