package xmlProjectSpringbootstarter.poruka;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorukaRepository extends MongoRepository<Poruka,String> {
}
