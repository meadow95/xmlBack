package xmlProjectSpringbootstarter.post;

import xmlProjectSpringbootstarter.komentari.Komentari;
import xmlProjectSpringbootstarter.korisnik.Korisnik;

import java.util.List;

import post.Komentar;

public class PostOut {

    private String id;
    private int likes;
    private int dislikes;
    private String lokacija;
    private String opis;
    private List<String> slike;
    private String korisnik;
    private List<Komentar> komentari;

    public PostOut(String id, int likes, int dislikes, String lokacija, String opis, List<String> slike, String korisnik) {

    	this.id = id;
    	this.likes = likes;
    	this.dislikes = dislikes;
    	this.lokacija = lokacija;
    	this.opis = opis;
    	this.slike = slike;
    	this.korisnik = korisnik;
    }


    public PostOut() {

    }

}
