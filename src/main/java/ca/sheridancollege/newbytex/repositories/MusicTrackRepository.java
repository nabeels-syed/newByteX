package ca.sheridancollege.newbytex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.newbytex.beans.Track;

public interface MusicTrackRepository extends JpaRepository<Track, String>{
	
	public Track findOneByTitle(String title);
	public Track findOneById(long id);
}
