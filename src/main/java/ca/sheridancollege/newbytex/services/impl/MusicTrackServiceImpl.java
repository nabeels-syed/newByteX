package ca.sheridancollege.newbytex.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonObjectId;
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
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.mongodb.client.gridfs.model.GridFSFile;

import ca.sheridancollege.newbytex.beans.MusicTrack;
import ca.sheridancollege.newbytex.repositories.MusicTrackRepository;
import ca.sheridancollege.newbytex.services.MusicTrackService;
import lombok.RequiredArgsConstructor;

@Service("trackServiceImpl")
@RequiredArgsConstructor
public class MusicTrackServiceImpl implements MusicTrackService {

	@Autowired
	private final GridFsTemplate gridFsTemplate;
	@Autowired
	private final GridFsOperations operations;

	private final MusicTrackRepository trackRepository;

	@Autowired
	AmazonS3 s3Client;

	@Value("${DO_SPACE_BUCKET")
	private String doSpaceBucket;
	
	private String dir = "track/";
	private String ext = ".mp3";

	@Override
	public List<MusicTrack> getAllTracks() {

		//List<GridFSFile> trackList = new ArrayList<GridFSFile>();
		//List<MusicTrack> tracks = new ArrayList<MusicTrack>();

		//this.gridFsTemplate.find(new Query(Criteria.where("metadata.fileType").is("MusicTrack"))).into(trackList);

		//for (GridFSFile gridTrack : trackList) {
		//	MusicTrack track = new MusicTrack();

		//	track.setId(((BsonObjectId) gridTrack.getId()).getValue().toString());
		//	track.setTitle(gridTrack.getMetadata().get("title").toString());
	//		track.setArtist(gridTrack.getMetadata().get("artist").toString());
	//		track.setReleaseDate(gridTrack.getMetadata().get("releaseDate").toString());
	//		try {
	//			track.setStream(operations.getResource(gridTrack).getInputStream());
	//		} catch (IllegalStateException | IOException e) {
	//			e.printStackTrace();
	//		}
	//		tracks.add(track);
	//	}
		
		return trackRepository.findAll();
	}

	@Override
	public MusicTrack updateTrack(String artist, String title, String id, String releaseDate) {

		MusicTrack updatedTrack = trackRepository.findOneById(id);
		if (updatedTrack != null) {
			if (artist.isBlank() && artist.isEmpty()) {
				updatedTrack.setArtist(artist);
			}
			if (title.isBlank() && title.isEmpty()) {
				updatedTrack.setTitle(title);
			}
			if (releaseDate.isBlank() && releaseDate.isEmpty()) {
				updatedTrack.setReleaseDate(releaseDate);
			}

			updatedTrack = trackRepository.save(updatedTrack);
		}

		return updatedTrack;
	}

	@Override
	public Boolean deleteTrack(String id) {

		//GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

		//if (file != null) {
		//	this.gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
		//	return true;
		//}
		String filepath = dir + id + ext;
		s3Client.deleteObject(doSpaceBucket, filepath);

		// delete data from mongodb
		MusicTrack track = trackRepository.findOneById(id);
		trackRepository.delete(track);
		


		return true;
	}

	// @Override
	// public String addTrack(String title, String artist, String releaseDate,
	// MultipartFile file) throws IOException {
//		DBObject metaData = new BasicDBObject();
//		metaData.put("type", "track");
//		metaData.put("title", title);
//		metaData.put("artist", artist);
//		metaData.put("fileType", "MusicTrack");
//		metaData.put("releaseDate", releaseDate);
//		ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(),
//				metaData);
//		return id.toString();
//	}

	@Override
	public String addTrack(String title, String artist, String releaseDate, MultipartFile multipartFile)
			throws IOException {

		// add track data to mongo database
		MusicTrack newTrack = new MusicTrack();
		newTrack.setArtist(artist);
		newTrack.setTitle(title);
		newTrack.setReleaseDate(releaseDate);
		newTrack = trackRepository.save(newTrack);
		
		String id = newTrack.getId();
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
	public MusicTrack getTrack(String id) throws IllegalStateException, IOException {
		//GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		String filepath = dir + id + ext;
		S3Object s3Object = s3Client.getObject(doSpaceBucket, filepath);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		
		MusicTrack track = trackRepository.findOneById(id);
		track.setStream(inputStream);
		s3Object.close();
		inputStream.close();
		return track;
	}
}
