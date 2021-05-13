package xmlProjectSpringbootstarter.korisnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{email}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getKorisnik(@PathVariable("email") String email) {
        Korisnik korisnik = korisnikService.findByEmail(email);
        System.out.println(korisnik.getEmail());
        return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/korisnik/{id}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getKorisnikById(@PathVariable("id") String id) {
        Korisnik korisnik = korisnikService.findOne(id);
        return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> registerKorisnik(@RequestBody Korisnik korisnik) {
        if (korisnikService.findByEmail(korisnik.getEmail()) == null) {
            List<String> uloge = new ArrayList<String>();
            uloge.add("ROLE_USER");
            korisnik.setUloge(uloge);
            korisnik.setEnabled(true);
            Korisnik k = korisnikService.insert(korisnik);
            return new ResponseEntity<Korisnik>(k, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/korisnici",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> insertKorisnik(@RequestBody Korisnik korisnik) throws Exception {
        List<String> uloge = new ArrayList<String>();
        uloge.add("ROLE_AGENT");
        korisnik.setUloge(uloge);
        korisnik.setEnabled(true);
        Korisnik createdKorisnik = this.korisnikService.create(korisnik);
        return new ResponseEntity<Korisnik>(createdKorisnik, HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/korisnici-list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Korisnik>> getAll() {

        List<Korisnik> korisnik = korisnikService.findAll();
        return new ResponseEntity<List<Korisnik>>(korisnik, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/korisnici-list/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getKorinsik(@PathVariable("id") String id) {

        Korisnik korisnik = this.korisnikService.findOne(id);
        if (korisnik.getUloge().contains("ROLE_USER")) {
            return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
        }else {
            return new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/korisnikUsername/{id}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getKorisnikUsername(@PathVariable("id") String id) {

    	List<Korisnik> korisnici = new ArrayList<Korisnik>();
        korisnici = this.korisnikService.findAll();
        
        for(Korisnik k : korisnici) {

        	if(k.getUsername().equals(id)) {
        		
        		return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
        		
        	}
        }

        return new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);
        
    }



    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/korisnici/{id}"
    )
    public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable("id") String id){
        this.korisnikService.delete(id);
        return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
    }



    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/korisnici-list/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> actDeactKorisnik(@PathVariable("id") String id, @RequestBody Korisnik korisnik) throws Exception{
        Korisnik korisnici = this.korisnikService.findOne(id);

        if(korisnici == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Korisnik updateKorisnik = this.korisnikService.activateDeactivate(korisnik);

        if (updateKorisnik == null) {
            return new ResponseEntity<Korisnik>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateKorisnik, HttpStatus.OK);
    }



}
