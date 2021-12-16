package ca.sheridancollege.newbytex.services;

import java.io.IOException;
import java.util.List;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.newbytex.beans.MusicTrack;

public interface MusicTrackService {

	List<MusicTrack> getAllTracks();

	MusicTrack updateTrack(String artist, String title, String id, String releaseDate);

	Boolean deleteTrack(String id);

	//String addTrack(String title, String artist, String releaseDate, MultipartFile file) throws IOException;

	MusicTrack getTrack(String id) throws IllegalStateException, IOException;

	String addTrack(String title, String artist, String releaseDate, MultipartFile multipartFile) throws IOException;
}
