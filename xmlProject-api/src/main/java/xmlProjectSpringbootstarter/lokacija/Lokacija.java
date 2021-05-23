package xmlProjectSpringbootstarter.lokacija;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Lokacije")
public class Lokacija {

    @Id
    private String id;
    private String naziv;

    public Lokacija(){

    }

    public Lokacija(String naziv) {
        this.naziv = naziv;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

 
}
