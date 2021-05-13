package xmlProjectSpringbootstarter.poruka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class PorukaController {

    @Autowired
    private PorukaService porukaService;

    @RequestMapping(
            method = RequestMethod.POST,
            value="/poruka",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Poruka> newPoruka(@RequestBody Poruka poruka) {
        Poruka poruka1 = porukaService.newPoruka(poruka);
        return new ResponseEntity<Poruka>(poruka1, HttpStatus.OK);
    }
}
