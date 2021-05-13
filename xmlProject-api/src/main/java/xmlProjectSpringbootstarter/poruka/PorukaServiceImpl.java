package xmlProjectSpringbootstarter.poruka;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlProjectSpringbootstarter.korisnik.Korisnik;
import xmlProjectSpringbootstarter.korisnik.KorisnikService;

import java.util.ArrayList;

@Service
public class PorukaServiceImpl implements PorukaService{

    @Autowired
    private PorukaRepository porukaRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public Poruka newPoruka(Poruka poruka) {


        Korisnik posaljilac = korisnikService.findOne(poruka.getPosaljilac());
        Korisnik primalac = korisnikService.findOne(poruka.getPrimalac());
        poruka.setPosaljilac(posaljilac.getIme());
        poruka.setPrimalac(primalac.getIme());
        Poruka por = porukaRepository.insert(poruka);
        if(posaljilac.getPoslate_poruke()==null) {
            posaljilac.setPoslate_poruke(new ArrayList<Poruka>());
        }
        if(primalac.getPrimljene_poruke()==null) {
            primalac.setPrimljene_poruke(new ArrayList<Poruka>());
        }
        posaljilac.getPoslate_poruke().add(por);
        primalac.getPrimljene_poruke().add(por);
        korisnikService.update(posaljilac);
        korisnikService.update(primalac);
        return por;
    }
}
