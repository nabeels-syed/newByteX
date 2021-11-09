package ca.sheridancollege.newbytex.dto;

import java.io.InputStream;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FlyerResponseDTO {

	private String id;

	private String eventName;

	private LocalDate eventDate;

	private String filetype;
	
	private InputStream stream;

}
