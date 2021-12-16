package ca.sheridancollege.newbytex.services.impl;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

import ca.sheridancollege.newbytex.beans.Flyer;
import ca.sheridancollege.newbytex.beans.MusicTrack;
import ca.sheridancollege.newbytex.repositories.FlyerRepository;
import ca.sheridancollege.newbytex.services.FlyerService;
import lombok.RequiredArgsConstructor;

@Service("flyerServiceImpl")
@RequiredArgsConstructor
public class FlyerServiceImpl implements FlyerService {

	@Autowired
	private final GridFsTemplate gridFsTemplate;
	@Autowired
	private final GridFsOperations operations;

	private final FlyerRepository flyerRepository;
	
	@Autowired
	AmazonS3 s3Client;

	@Value("${DO_SPACE_BUCKET}")
	private String doSpaceBucket;
	
	private String dir = "flyer/";
	private String ext = ".jpg";
	
	@Override
	public List<Flyer> getAllFlyers() {

		//List<GridFSFile> flyerList = new ArrayList<GridFSFile>();
		//List<Flyer> flyers = new ArrayList<Flyer>();
		
	//	this.gridFsTemplate.find(new Query(Criteria.where("metadata.type").is("flyer")))
	//		.sort(new BasicDBObject("metadata.eventDate", 1)).into(flyerList);
	//	
	//	for (GridFSFile flyerFile : flyerList) {
	//		Flyer flyer = new Flyer();
//
//			flyer.setId(((BsonObjectId) flyerFile.getId()).getValue().toString());
//			flyer.setEventName(flyerFile.getMetadata().get("eventName").toString());
//			flyer.setEventDate(flyerFile.getMetadata().get("eventDate").toString());
//
//			try {
//				flyer.setStream(operations.getResource(flyerFile).getInputStream());
//			} catch (IllegalStateException | IOException e) {
//				e.printStackTrace();
//			}
//			flyers.add(flyer);
//		}
		
		return flyerRepository.findAll();
	}

	@Override
	public Boolean deleteFlyer(String id) {
		//this.gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
		String filepath = dir + id + ext;
		s3Client.deleteObject(doSpaceBucket, filepath);

		// delete data from mongodb
		Flyer flyer = flyerRepository.findOneById(id);
		flyerRepository.delete(flyer);
		return true;
	}

	@Override
	public String addFlyer(String eventName, String eventDate, MultipartFile multipartFile) throws IOException {
		
		//DBObject metaData = new BasicDBObject();
		//metaData.put("type", "flyer");
		//metaData.put("eventName", eventName);
		//metaData.put("eventDate", eventDate.toString());
		//metaData.put("fileType", "Flyer");
		//ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(),
	//			metaData);
//		return id.toString();
		
		// add track data to mongo database
				Flyer flyer = new Flyer();
				flyer.setEventName(eventName);
				flyer.setEventDate(eventDate);
				flyer = flyerRepository.save(flyer);
				
				String id = flyer.getId();
				String filepath = dir + id + ext;
			

				// upload file to storage space
				ObjectMetadata metadata = new ObjectMetadata();

				metadata.setContentLength(multipartFile.getInputStream().available());

				if (multipartFile.getContentType() != null && !"".equals(multipartFile.getContentType())) {
					metadata.setContentType(multipartFile.getContentType());
				}

				s3Client.putObject(new PutObjectRequest(doSpaceBucket, filepath, multipartFile.getInputStream(), metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				
				return id;
	}

	@Override
	public Flyer findFlyerById(String id) throws IllegalStateException, IOException {

		//GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		//Flyer flyer = new Flyer();
		//flyer.setEventName(file.getMetadata().get("eventName").toString());
		//flyer.setEventDate(file.getMetadata().get("eventDate").toString());
		//flyer.setStream(operations.getResource(file).getInputStream());
		//return flyer;
		
		String filepath = dir + id + ext;
		S3Object s3Object = s3Client.getObject(doSpaceBucket, filepath);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		
		Flyer flyer = flyerRepository.findOneById(id);
		flyer.setStream(inputStream);
		s3Object.close();
		inputStream.close();
		return flyer;
	}

}
