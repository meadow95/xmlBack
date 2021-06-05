package postservice.commentmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Comments")
public class Comment {

    @Id
    private String id;
    private String content;
    private String postID;
    private String userUsername;

    public Comment(){

    }

    public Comment(String content, String postID, String userUsername) {
        this.content = content;
        this.postID = postID;
        this.userUsername = userUsername;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

 
}
