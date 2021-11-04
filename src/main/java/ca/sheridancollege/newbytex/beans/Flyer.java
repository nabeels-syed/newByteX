package ca.sheridancollege.newbytex.beans;

import java.io.InputStream;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;

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
	private String eventDate;
	
	private String fileType;
	
	private InputStream stream;
	
}
