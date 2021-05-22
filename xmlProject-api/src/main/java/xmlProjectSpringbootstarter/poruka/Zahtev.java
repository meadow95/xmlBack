package xmlProjectSpringbootstarter.poruka;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Zahtev")
public class Zahtev {
    @Id
    private String id;
    private String posaljilac;
    private String primalac;

    public Zahtev(String posaljilac, String primalac) {
        this.posaljilac = posaljilac;
        this.primalac = primalac;
    }

    public Zahtev() {

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
