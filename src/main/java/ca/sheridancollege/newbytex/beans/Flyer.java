package ca.sheridancollege.newbytex.beans;

import java.io.InputStream;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.Data;


@Data
@Document(collection="flyers")
public class Flyer {
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Event name is mandatory")
	private String eventName;
	
	@NotNull
	@NotBlank(message = "Event Date is mandatory")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate eventDate;
	
	private String fileType;
	
	private InputStream stream;
	
}
