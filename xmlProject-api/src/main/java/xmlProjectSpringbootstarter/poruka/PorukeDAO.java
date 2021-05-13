package xmlProjectSpringbootstarter.poruka;

import org.springframework.stereotype.Repository;

@Repository
public class PorukeDAO {
    public static Poruka create(Poruka poruka) {
        return poruka;
    }
}
