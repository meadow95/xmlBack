package xmlProjectSpringbootstarter.poruka;

import javax.jws.WebService;

@WebService
public interface PorukeSOAPService {

    Poruka create(Poruka poruka);
}
