package ca.sheridancollege.newbytex.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public List<Flyer> getAllFlyers() {

		List<GridFSFile> flyerList = new ArrayList<GridFSFile>();
		List<Flyer> flyers = new ArrayList<Flyer>();
		
		this.gridFsTemplate.find(new Query(Criteria.where("metadata.fileType").is("Flyer"))).into(flyerList);
		
		for (GridFSFile gridTrack : flyerList) {
			Flyer flyer = new Flyer();

			flyer.setId(((BsonObjectId) gridTrack.getId()).getValue().toString());
			flyer.setEventName(gridTrack.getMetadata().get("eventName").toString());
			flyer.setEventDate(gridTrack.getMetadata().get("eventDate").toString());

			try {
				flyer.setStream(operations.getResource(gridTrack).getInputStream());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			flyers.add(flyer);
		}
		
		return flyers;
	}

	@Override
	public Boolean deleteFlyer(String id) {

		this.gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
		return true;

	}

	@Override
	public String addFlyer(String eventName, String eventDate, MultipartFile file) throws IOException {

		DBObject metaData = new BasicDBObject();
		metaData.put("type", "flyer");
		metaData.put("eventName", eventName);
		metaData.put("eventDate", eventDate);
		metaData.put("fileType", "Flyer");
		ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(),
				metaData);
		return id.toString();
	}

	@Override
	public Flyer findFlyerById(String id) throws IllegalStateException, IOException {

		GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		Flyer flyer = new Flyer();
		flyer.setEventName(file.getMetadata().get("eventName").toString());
		flyer.setEventDate(file.getMetadata().get("eventDate").toString());
		flyer.setStream(operations.getResource(file).getInputStream());
		return flyer;
	}

}
