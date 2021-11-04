package ca.sheridancollege.newbytex.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.newbytex.beans.Flyer;

public interface FlyerService {
	
	List<Flyer> getAllFlyers();
	Boolean deleteFlyer(String id);
    String addFlyer(String eventName, String eventDate, MultipartFile file) throws IOException;
    Flyer findFlyerById(String id) throws IllegalStateException, IOException;

}
