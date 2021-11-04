package ca.sheridancollege.newbytex.dto;

import java.io.InputStream;

import lombok.Data;

@Data
public class FlyerResponseDTO {

	private String id;

	private String eventName;

	private String eventDate;

	private String filetype;
	
	private InputStream stream;

}
