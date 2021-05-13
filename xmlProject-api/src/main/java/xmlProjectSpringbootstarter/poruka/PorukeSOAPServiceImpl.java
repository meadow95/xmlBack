package xmlProjectSpringbootstarter.poruka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService (targetNamespace="http://xmlProjectSpringbootstarter/", serviceName="PorukaService", endpointInterface="xmlProjectSpringbootstarter.poruka.PorukeSOAPService")
public class PorukeSOAPServiceImpl implements PorukeSOAPService{

    @Autowired
    PorukeDAO porukeDAO;

    @Override
    public Poruka create(Poruka poruka) {
        Poruka acc = PorukeDAO.create(poruka);
        System.out.println("OK!");
        return acc;
    }
}
