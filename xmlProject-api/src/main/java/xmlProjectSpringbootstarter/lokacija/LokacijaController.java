package xmlProjectSpringbootstarter.lokacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import xmlProjectSpringbootstarter.post.Post;
import xmlProjectSpringbootstarter.post.PostService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LokacijaController {

    @Autowired
    private LokacijaService lokacijaService;
    
    @Autowired
    private PostService postService;
/*
    public LokacijaController(KomentariService komentariService){
        this.komentariService = komentariService;
    }
*/


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/lokacije",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Lokacija>> getAll() {
        List<Lokacija> komentari = lokacijaService.findAll();
        return new ResponseEntity<List<Lokacija>>(komentari, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/lokacija/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lokacija> getKomentari(@PathVariable("id") String id) {
    	Lokacija komentari = this.lokacijaService.findOne(id);
        if(komentari == null){
            return new ResponseEntity<Lokacija>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Lokacija>(komentari, HttpStatus.OK);
    }

}
