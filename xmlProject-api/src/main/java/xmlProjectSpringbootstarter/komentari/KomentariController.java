package xmlProjectSpringbootstarter.komentari;

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
public class KomentariController {

    @Autowired
    private KomentariService komentariService;
    
    @Autowired
    private PostService postService;

    public KomentariController(KomentariService komentariService){
        this.komentariService = komentariService;
    }



    @RequestMapping(
            method = RequestMethod.GET,
            value ="/komentari",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Komentari>> getAll() {
        List<Komentari> komentari = komentariService.findAll();
        return new ResponseEntity<List<Komentari>>(komentari, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/komentari/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Komentari> getKomentari(@PathVariable("id") String id) {
        Komentari komentari = this.komentariService.findOne(id);
        if(komentari == null){
            return new ResponseEntity<Komentari>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Komentari>(komentari, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/komentari/{idSmestaj}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Komentari> insertKomentar(@PathVariable ("idSmestaj") String id, @RequestBody Komentari komentari) throws Exception{
        komentari.setSmestajId(id);
        Komentari createdKomentar  = this.komentariService.create(komentari);
        return new ResponseEntity<Komentari>(createdKomentar, HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/komentari/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Komentari> updateKomentar(@PathVariable("id") String id, @RequestBody Komentari komentar) throws Exception{

        Post smestaj = postService.findOne(komentar.getSmestajId());

        komentar.setOdobren(true);
        Komentari updateKomentar = this.komentariService.update(komentar);
 //       smestaj.getKomentari().add(updateKomentar);
        postService.update(smestaj);

        if (updateKomentar == null) {
            return new ResponseEntity<Komentari>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateKomentar, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/komentari/{id}"
    )
    public ResponseEntity<Komentari> deleteKomentar(@PathVariable("id") String id){
        this.komentariService.delete(id);
        return new ResponseEntity<Komentari>(HttpStatus.NO_CONTENT);
    }
}
