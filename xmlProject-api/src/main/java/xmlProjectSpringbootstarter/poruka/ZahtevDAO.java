package xmlProjectSpringbootstarter.poruka;

import org.springframework.stereotype.Repository;

@Repository
public class ZahtevDAO {
    public static Zahtev create(Zahtev poruka) {
        return poruka;
    }
}
