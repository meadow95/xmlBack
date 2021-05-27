package xmlProjectSpringbootstarter.korisnik;

import com.fasterxml.jackson.annotation.JsonIgnore;

import post.Post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Korisnici")
public class Korisnik {

    @Id
    private String id;
    private String username;
	private String ime;
    private String prezime;
    private String email;
    private String password;
    private String brtel;
    private String adresa;
	private String pmb;
    private List<Korisnik> pratioci;
    private List<Korisnik> pratim;
	private List<Post> postovi;
	private String privatniProfil;
	

	@JsonIgnore
    private boolean enabled;
    @JsonIgnore
    private List<String> uloge;

    public Korisnik() {

    }

    public Korisnik(String username, String ime, String prezime, String email, String password, String brtel, boolean enabled, List<String> uloge) {
        this.username = username;
    	this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.brtel = brtel;
        this.adresa = adresa;
        this.pmb = pmb;
        this.enabled = enabled;
        this.uloge = uloge;
        this.privatniProfil = "no";
    }
    
    public String getPrivatniProfil() {
		return privatniProfil;
	}

	public void setPrivatniProfil(String privatniProfil) {
		this.privatniProfil = privatniProfil;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    public List<Post> getPostovi() {
		return postovi;
	}

	public void setPostovi(List<Post> postovi) {
		this.postovi = postovi;
	}

	public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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

    public String getBrtel() {
        return brtel;
    }

    public void setBrtel(String brtel) {
        this.brtel = brtel;
    }

    public String getPmb() {
        return pmb;
    }

    public void setPmb(String pmb) {
        this.pmb = pmb;
    }

    public List<String> getUloge() {
        return uloge;
    }

    public void setUloge(List<String> uloge) {
        this.uloge = uloge;
    }
    
    public List<Korisnik> getPratioci() {
		return pratioci;
	}

	public void setPratioci(List<Korisnik> pratioci) {
		this.pratioci = pratioci;
	}

	public List<Korisnik> getPratim() {
		return pratim;
	}

	public void setPratim(List<Korisnik> pratim) {
		this.pratim = pratim;
	}

	public void setId(String id) {
		this.id = id;
	}
}
