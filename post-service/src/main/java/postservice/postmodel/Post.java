package postservice.postmodel;

import org.bson.types.Binary;
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
    private Binary picture;
    private String pic;
	private String user;
	private String identificationNum;
    private List<Comment> comments;
    private List<String> tags;


	public Post(int likes, int dislikes, String location, String description, String user) {

    	this.likes = likes;
    	this.dislikes = dislikes;
    	this.location = location;
    	this.description = description;
    	this.user = user;
    }
    

	public Post() {
    	
    	super();
    	
    }
	
    public String getIdentificationNum() {
		return identificationNum;
	}

	public void setIdentificationNum(String identificationNum) {
		this.identificationNum = identificationNum;
	}
    
    public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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

	public Binary getPicture() {
		return picture;
	}

	public void setPicture(Binary picture) {
		this.picture = picture;
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

