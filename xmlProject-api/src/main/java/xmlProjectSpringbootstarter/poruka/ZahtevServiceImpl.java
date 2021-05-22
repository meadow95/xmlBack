package xmlProjectSpringbootstarter.poruka;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlProjectSpringbootstarter.korisnik.Korisnik;
import xmlProjectSpringbootstarter.korisnik.KorisnikService;

import java.util.ArrayList;

@Service
public class ZahtevServiceImpl implements ZahtevService{

    @Autowired
    private ZahtevRepository porukaRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public Zahtev newPoruka(Zahtev poruka) {


        Korisnik posaljilac = korisnikService.findOne(poruka.getPosaljilac());
        Korisnik primalac = korisnikService.findOne(poruka.getPrimalac());
        poruka.setPosaljilac(posaljilac.getIme());
        poruka.setPrimalac(primalac.getIme());
        Zahtev por = porukaRepository.insert(poruka);

        korisnikService.update(posaljilac);
        korisnikService.update(primalac);
        return por;
    }
}
