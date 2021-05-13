package xmlProjectSpringbootstarter.korisnik;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KorisnikRepository extends MongoRepository<Korisnik, String> {

    Korisnik findByEmail(String email);

}
