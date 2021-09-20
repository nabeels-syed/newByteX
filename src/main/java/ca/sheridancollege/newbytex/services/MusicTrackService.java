package ca.sheridancollege.newbytex.services;

import java.util.List;

import ca.sheridancollege.newbytex.beans.Track;

public interface MusicTrackService {
	
	List<Track> findAllTracks();
	Track findTrack(long id);
	Track updateTrack(String artist, String title, String filename, long id, String releaseDate, boolean isFavourited);
	Boolean deleteTrack(long id);
	Track createTrack(String artist, String title, String filename, String releaseDate, boolean isFavourited);
}
