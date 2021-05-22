package xmlProjectSpringbootstarter.poruka;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtevRepository extends MongoRepository<Zahtev,String> {
}
