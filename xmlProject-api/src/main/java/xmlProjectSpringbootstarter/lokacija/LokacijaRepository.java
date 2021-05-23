package xmlProjectSpringbootstarter.lokacija;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokacijaRepository extends MongoRepository<Lokacija, String> {
}
