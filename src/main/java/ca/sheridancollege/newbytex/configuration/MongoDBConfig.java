package ca.sheridancollege.newbytex.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@Profile("mongodb")
@EnableMongoAuditing
public class MongoDBConfig {
	
	//MongoClient mongoClient = MongoClients.create("mongodb+srv://indmixadmin:testPassword91@cluster0.ubona.mongodb.net/indmix?retryWrites=true&w=majority");
	
	

}
