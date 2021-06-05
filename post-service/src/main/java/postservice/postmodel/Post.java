package postservice.postmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.stream.events.Comment;

@Document(collection = "Post")
public class Post {

    @Id
    private String id;
    private int likes;
    private int dislikes;
    private String location;
    private String description;
    private List<String> pictures;
    private String user;
    private List<Comment> comments;

	public Post(int likes, int dislikes, String location, String description, List<String> pictures, String user) {

    	this.likes = likes;
    	this.dislikes = dislikes;
    	this.location = location;
    	this.description = description;
    	this.pictures = pictures;
    	this.user = user;
    }
    
    public Post() {
    	
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
    public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


}

