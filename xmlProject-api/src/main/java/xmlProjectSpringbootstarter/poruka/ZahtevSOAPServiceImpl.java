package xmlProjectSpringbootstarter.poruka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService (targetNamespace="http://xmlProjectSpringbootstarter/", serviceName="ZahtevService", endpointInterface="xmlProjectSpringbootstarter.poruka.ZahtevSOAPService")
public class ZahtevSOAPServiceImpl implements ZahtevSOAPService{

    @Autowired
    ZahtevDAO porukeDAO;

    @Override
    public Zahtev create(Zahtev poruka) {
    	Zahtev acc = ZahtevDAO.create(poruka);
        System.out.println("OK!");
        return acc;
    }
}
