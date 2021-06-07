package userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String username;
	private String name;
    private String surname;
    private String email;
    private String password;
    private List<User> followers;
	private List<User> following;
	private List<String> posts;
	private String privateProfile;
	

    public User() {

    }

    public User(String username, String name, String surname, String email, String password) {
        this.username = username;
    	this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
    
	public List<String> getPosts() {
		return posts;
	}

	public void setPosts(List<String> posts) {
		this.posts = posts;
	}
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public String getPrivateProfile() {
		return privateProfile;
	}

	public void setPrivateProfile(String privateProfile) {
		this.privateProfile = privateProfile;
	}

}
