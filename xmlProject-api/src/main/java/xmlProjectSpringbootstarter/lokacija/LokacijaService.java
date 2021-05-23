package xmlProjectSpringbootstarter.lokacija;

import java.util.List;

public interface LokacijaService {
    List<Lokacija> findAll();
    Lokacija findOne(String id);
    Lokacija create(Lokacija komentar);
    Lokacija update(Lokacija komentar) throws Exception;
    void delete(String id);
}
