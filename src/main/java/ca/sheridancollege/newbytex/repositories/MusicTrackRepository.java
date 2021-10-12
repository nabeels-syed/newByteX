package ca.sheridancollege.newbytex.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.newbytex.beans.MusicTrack;
@Repository
public interface MusicTrackRepository extends MongoRepository<MusicTrack, String>{
	
	public MusicTrack findOneById(String id);
}
