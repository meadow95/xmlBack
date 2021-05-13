package xmlProjectSpringbootstarter.korisnik;

import java.util.List;

public interface KorisnikService{
    Korisnik findOne(String id);
    List<Korisnik> findAll();
    Korisnik insert(Korisnik k);
    Korisnik findByEmail(String email);
    Korisnik create(Korisnik korisnik) throws Exception;
    Korisnik update(Korisnik korisnik);
    Korisnik activateDeactivate(Korisnik korisnik) throws Exception;
    void delete(String id);

}
