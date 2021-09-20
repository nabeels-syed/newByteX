package ca.sheridancollege.newbytex.dto;

import lombok.Data;

@Data
public class MusicTrackRequestDTO {
	
	private String artist;
	private String title;
	private String filename;
	private Boolean isFavourited;
	private long id;

}
