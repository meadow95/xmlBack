package userservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements /*UserDetailsService,*/UserService {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
/*
    @Override
    public User findOne(String id) {
        return userRepository.findById(id);
    }
*/

    public User insert(User k) {
        k.setPassword(bcryptEncoder.encode(k.getPassword()));
        return userRepository.insert(k);
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


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

/*
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User korisnik = findByEmail(s);
        if(korisnik == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(korisnik.getEmail(), korisnik.getPassword(), authorities);

        return userDetails;
    }
*/

    public User create(User user) throws Exception {
        User savedKorisnik = this.userRepository.insert(user);
        return savedKorisnik;
    }

    public User update(User user) {
        return userRepository.save(user);
    }


    public List<User> findAll(){
        List<User> korisnik = this.userRepository.findAll();
        return korisnik;
    }

	public User findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}



}
