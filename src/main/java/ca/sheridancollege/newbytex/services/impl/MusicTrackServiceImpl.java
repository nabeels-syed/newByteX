package ca.sheridancollege.newbytex.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.BSONObject;
import org.bson.BasicBSONDecoder;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Function;
import com.mongodb.client.gridfs.model.GridFSFile;

import ca.sheridancollege.newbytex.beans.MusicTrack;
import ca.sheridancollege.newbytex.repositories.MusicTrackRepository;
import ca.sheridancollege.newbytex.repositories.UserRepository;
import ca.sheridancollege.newbytex.security.JwtProvider;
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

	@Override
	public List<MusicTrack> getAllTracks() {

		List<GridFSFile> trackList = new ArrayList<GridFSFile>();
		List<MusicTrack> tracks = new ArrayList<MusicTrack>();
		
		this.gridFsTemplate.find(new Query(Criteria.where("metadata.fileType").is("MusicTrack"))).into(trackList);
		
		for (GridFSFile gridTrack : trackList) {
			MusicTrack track = new MusicTrack();

			track.setId(((BsonObjectId) gridTrack.getId()).getValue().toString());
			track.setTitle(gridTrack.getMetadata().get("title").toString());
			track.setArtist(gridTrack.getMetadata().get("artist").toString());
			track.setReleaseDate(gridTrack.getMetadata().get("releaseDate").toString());
			try {
				track.setStream(operations.getResource(gridTrack).getInputStream());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			tracks.add(track);
		}
		
		return tracks;
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
		
		GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		
		if (file != null) {
			this.gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
			return true;
		}
		return false;
	}

	@Override
	public String addTrack(String title, String artist, String releaseDate, MultipartFile file) throws IOException {
		DBObject metaData = new BasicDBObject();
		metaData.put("type", "track");
		metaData.put("title", title);
		metaData.put("artist", artist);
		metaData.put("fileType", "MusicTrack");
		metaData.put("releaseDate", releaseDate);
		ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);
		return id.toString();
	}

	@Override
	public MusicTrack getTrack(String id) throws IllegalStateException, IOException {
		GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		MusicTrack track = new MusicTrack();
		track.setTitle(file.getMetadata().get("title").toString());
		track.setArtist(file.getMetadata().get("artist").toString());
		track.setReleaseDate(file.getMetadata().get("releaseDate").toString());
		track.setStream(operations.getResource(file).getInputStream());
		return track;
	}
}
