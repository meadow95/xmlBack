package xmlProjectSpringbootstarter.poruka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ZahtevController {

    @Autowired
    private ZahtevService ZahtevService;

    @RequestMapping(
            method = RequestMethod.POST,
            value="/zahtev",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Zahtev> newPoruka(@RequestBody Zahtev poruka) {
    	Zahtev poruka1 = ZahtevService.newPoruka(poruka);
        return new ResponseEntity<Zahtev>(poruka1, HttpStatus.OK);
    }
}
