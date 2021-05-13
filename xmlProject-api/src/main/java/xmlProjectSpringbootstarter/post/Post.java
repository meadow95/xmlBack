package xmlProjectSpringbootstarter.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import post.Komentar;
import xmlProjectSpringbootstarter.komentari.Komentari;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

@Document(collection = "Post")
public class Post {

    @Id
    private String id;
    private int likes;
    private int dislikes;
    private String lokacija;
    private String opis;
    private List<String> slike;
    private String korisnik;
    private List<Komentar> komentari;

    public Post(int likes, int dislikes, String lokacija, String opis, List<String> slike, String korisnik) {

    	this.likes = likes;
    	this.dislikes = dislikes;
    	this.lokacija = lokacija;
    	this.opis = opis;
    	this.slike = slike;
    	this.korisnik = korisnik;
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

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<String> getSlike() {
		return slike;
	}

	public void setSlike(List<String> slike) {
		this.slike = slike;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public List<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}


}

