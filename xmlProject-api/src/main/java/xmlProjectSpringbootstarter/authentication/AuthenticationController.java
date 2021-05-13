package xmlProjectSpringbootstarter.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xmlProjectSpringbootstarter.komentari.Komentari;
import xmlProjectSpringbootstarter.komentari.KomentariService;
import xmlProjectSpringbootstarter.korisnik.Korisnik;
import xmlProjectSpringbootstarter.korisnik.KorisnikService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KomentariService komentariService;


    @RequestMapping(value = "/generate-token", method= RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );


        /*Termin januar = new Termin(1,500);
        Termin februar = new Termin(2,600);
        Termin mart = new Termin(3,200);
        Termin april = new Termin(4,100);
        Termin maj = new Termin(5,50);
        Termin jun = new Termin(6,250);
        Termin jul = new Termin(7,300);
        Termin avgust = new Termin(8,20);
        Termin septembar = new Termin(9,5);
        Termin oktobar = new Termin(10,7);
        Termin novembar = new Termin(11,240);
        Termin decembar = new Termin(12,19);
        Termin januar1 = terminService.create(januar);
        Termin februar1 = terminService.create(februar);
        Termin mart1 = terminService.create(mart);
        Termin april1 = terminService.create(april);
        Termin maj1 = terminService.create(maj);
        Termin jun1 = terminService.create(jun);
        Termin jul1 = terminService.create(jul);
        Termin avgust1 = terminService.create(avgust);
        Termin septembar1 = terminService.create(septembar);
        Termin oktobar1 = terminService.create(oktobar);
        Termin novembar1 = terminService.create(novembar);
        Termin decembar1 = terminService.create(decembar);

        List<Smestaj> smestaji = smestajService.findAll();
        Smestaj smestaj = smestaji.get(0);
        smestaj.getTermini().add(januar1);
        smestaj.getTermini().add(februar1);
        smestaj.getTermini().add(mart1);
        smestaj.getTermini().add(april1);
        smestaj.getTermini().add(maj1);
        smestaj.getTermini().add(jun1);
        smestaj.getTermini().add(jul1);
        smestaj.getTermini().add(avgust1);
        smestaj.getTermini().add(septembar1);
        smestaj.getTermini().add(oktobar1);
        smestaj.getTermini().add(novembar1);
        smestaj.getTermini().add(decembar1);

        smestajService.update(smestaj);


        Smestaj smestaj = smestajService.findOne("5b0d88029e81b71f6c086ca0");
        Komentari komentar = komentariService.findOne("5b0bda762569022d187739fe");
        smestaj.getKomentari().add(komentar);
        smestajService.update(smestaj);
        {
            List<Drzava> drzave = drzavaService.findAll();
            NaseljenoMesto nas = new NaseljenoMesto("Novi Sad","21000", drzave.get(0).getId());
            nas = naseljenoMestoService.insert(nas);
            NaseljenoMesto nas1 = new NaseljenoMesto("Beograd", "11000", drzave.get(0).getId());
            nas1 = naseljenoMestoService.insert(nas1);
            String datumDol = "01-07-2018";
            String datumOdl = "01-08-2018";
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            Date date1 = null;
            try {
                date = format.parse(datumDol);
                date1 = format.parse(datumOdl);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Rezervacija rez = new Rezervacija(date, date1);
            Zauzetost zau = new Zauzetost(date,date1);
            List<Zauzetost> zauzeto = new ArrayList<Zauzetost>();
            zauzeto.add(zau);
            rez = rezervacijaService.create(rez);
            List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
            rezervacije.add(rez);
            String agent = korisnikService.findByEmail("iiiii@gmail.com").getId();
            String tip_smestaja = tipsmestajaService.findOne("5b02e093b576a0187c8f25ce").getId();
            String kategorijaSmestaja = kategorijaService.findOne("5b02e46ab576a0187c8f25d1").getId();
            Termin termin = new Termin("Januar", 500);
            termin = terminService.create(termin);
            List<Termin> termini = new ArrayList<Termin>();
            termini.add(termin);
            DodatnaUsluga dod = new DodatnaUsluga("Klima");
            dod = dodatnaUslugaService.create(dod);
            List<DodatnaUsluga> dodatneUsluge = new ArrayList<DodatnaUsluga>();
            dodatneUsluge.add(dod);

            Smestaj smestaj = new Smestaj("Hotel Park", 4, nas.getId(),"Veoma lep hotel","", rezervacije,zauzeto,new ArrayList<Komentari>(), agent, tip_smestaja, kategorijaSmestaja, termini, dodatneUsluge);
            smestajService.create(smestaj);

        }*/

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Korisnik user = korisnikService.findByEmail(loginUser.getUsername());
        if(user.isEnabled()) {
            final String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new AuthToken(token));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


