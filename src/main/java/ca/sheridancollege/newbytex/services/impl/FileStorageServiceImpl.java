package ca.sheridancollege.newbytex.services.impl;

import java.awt.Image;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import ca.sheridancollege.newbytex.repositories.FlyerRepository;
import ca.sheridancollege.newbytex.repositories.MusicTrackRepository;
import ca.sheridancollege.newbytex.services.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	AmazonS3 s3Client;
	
	@Autowired
	MusicTrackRepository musicRepo;
	
	@Autowired
	FlyerRepository flyerRepo;
	
	@Value("#{DO_SPACE_BUCKET")
	private String doSpaceBucket;
	
	String FOLDER = "files/";
	
	
	public void saveImageToServer(MultipartFile multipartFile, String key) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(multipartFile.getInputStream().available());
		if (multipartFile.getContentType() != null && !"".equals(multipartFile.getContentType())) {
			metadata.setContentType(multipartFile.getContentType());
		}
		s3Client.putObject(new PutObjectRequest(doSpaceBucket, key, multipartFile.getInputStream(), metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
}
