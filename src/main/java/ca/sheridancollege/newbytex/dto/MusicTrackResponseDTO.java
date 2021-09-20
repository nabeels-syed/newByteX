package ca.sheridancollege.newbytex.dto;

import lombok.Data;

@Data
public class MusicTrackResponseDTO {
	
	private String artist;
	private String title;
	private String fileName;
	private Boolean isFavourited;
	private long id;

}
