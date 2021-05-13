package xmlProjectSpringbootstarter.korisnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "korisnikService")
public class KorisnikServiceImpl implements UserDetailsService,KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public Korisnik findOne(String id) {
        return korisnikRepository.findOne(id);
    }

    @Override
    public Korisnik insert(Korisnik k) {
        k.setPassword(bcryptEncoder.encode(k.getPassword()));
        return korisnikRepository.insert(k);
    }

    /*@Override
    public Korisnik findByEmail(String email) {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        for(Korisnik k: korisnici) {
            if(k.getEmail().equals(email)) {
                return k;
            }
        }
        return null;
    }*/

    @Override
    public Korisnik findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Korisnik korisnik = findByEmail(s);
        if(korisnik == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(String r: korisnik.getUloge()) {
            authorities.add(new SimpleGrantedAuthority(r));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(korisnik.getEmail(), korisnik.getPassword(), authorities);

        return userDetails;
    }



    @Override
    public Korisnik create(Korisnik korisnik) throws Exception {
        Korisnik savedKorisnik = this.korisnikRepository.insert(korisnik);
        return savedKorisnik;
    }

    @Override
    public Korisnik update(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }


    @Override
    public List<Korisnik> findAll(){
        List<Korisnik> korisnik = this.korisnikRepository.findAll();
        return korisnik;
    }

    @Override
    public void delete(String id) {
        this.korisnikRepository.delete(id);
    }



    @Override
    public Korisnik activateDeactivate(Korisnik korisnik) throws Exception {
        Korisnik korisnikUpdt = this.korisnikRepository.findOne(korisnik.getId());

        if(korisnikUpdt == null){
            throw new Exception("Nije pronadjen korisnik.");
        }


        if(korisnikUpdt.isEnabled() == true) {
            korisnikUpdt.setEnabled(false);
        }else if(korisnikUpdt.isEnabled() == false){
            korisnikUpdt.setEnabled(true);
        }


        Korisnik updateKorisnik = this.korisnikRepository.save(korisnikUpdt);
        return updateKorisnik;
    }


}
