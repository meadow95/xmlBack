package xmlProjectSpringbootstarter.poruka;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Poruke")
public class Poruka {
    @Id
    private String id;
    private String sadrzaj;
    private String posaljilac;
    private String primalac;

    public Poruka(String sadrzaj, String posaljilac, String primalac) {
        this.sadrzaj = sadrzaj;
        this.posaljilac = posaljilac;
        this.primalac = primalac;
    }

    public Poruka() {

    }

    public String getId() {
        return id;
    }

    public String getSadrzaj() {

        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getPosaljilac() {
        return posaljilac;
    }

    public void setPosaljilac(String posaljilac) {
        this.posaljilac = posaljilac;
    }

    public String getPrimalac() {
        return primalac;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }
}
