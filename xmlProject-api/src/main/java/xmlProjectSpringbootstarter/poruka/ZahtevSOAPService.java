package xmlProjectSpringbootstarter.poruka;

import javax.jws.WebService;

@WebService
public interface ZahtevSOAPService {

	Zahtev create(Zahtev poruka);
}
