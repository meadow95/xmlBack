package xmlProjectSpringbootstarter.lokacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokacijaServiceImpl implements LokacijaService{

    @Autowired
    private LokacijaRepository komentariRepository;


    @Override
    public List<Lokacija> findAll(){
        List<Lokacija> komentar = this.komentariRepository.findAll();
        return komentar;
    }

    @Override
    public Lokacija findOne(String id){
    	Lokacija komentar = this.komentariRepository.findOne(id);
        return komentar;
    }

    @Override
    public Lokacija create(Lokacija komentar) {
    	Lokacija savedKomentar = this.komentariRepository.insert(komentar);
        return savedKomentar;
    }

    @Override
    public Lokacija update(Lokacija komentar) throws Exception {
    	Lokacija updateKomentari = this.komentariRepository.save(komentar);
        return updateKomentari;
    }

    @Override
    public void delete(String id) {
        this.komentariRepository.delete(id);
    }
}
