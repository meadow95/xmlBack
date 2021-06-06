package userservice.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.stream.events.Comment;



public class PostDTO {

    private String id;
    private int likes;
    private int dislikes;
    private String location;
    private String description;
    private Binary picture;
    private String user;
    private List<Comment> comments;
    private List<String> tags;



    
    public PostDTO() {
    	
    	super();
    	
    }

	
	public String getId() {
		return id;
	}


}

