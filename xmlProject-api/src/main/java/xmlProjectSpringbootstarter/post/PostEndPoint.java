package xmlProjectSpringbootstarter.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import post.*;
import xmlProjectSpringbootstarter.komentari.Komentari;
import xmlProjectSpringbootstarter.komentari.KomentariService;
import xmlProjectSpringbootstarter.korisnik.Korisnik;
import xmlProjectSpringbootstarter.korisnik.KorisnikService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Endpoint
public class PostEndPoint {
    private static final String NAMESPACE_URI = "post";

    private PostService postService;

    /*@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSmestajRequest")
    @ResponsePayload
    public AddSmestajResponse addSmestaj(@RequestPayload Smestaj response) {
        AddSmestajResponse responsee = new AddSmestajResponse();

    }*/


    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KomentariService komentariService;



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSmestajRequest")
    @ResponsePayload
    public AddPostResponse addSmestaj(@RequestPayload AddPostRequest request) {

    	AddPostResponse response = new AddPostResponse();

        Post smestaj = new Post();
//        smestaj.setNaziv(request.getSmestaj().getNaziv());
//        smestaj.setKapacitet(request.getSmestaj().getKapacitet());
//       smestaj.setNaseljeno_mesto(request.getSmestaj().getNaseljenoMesto().getId());
 //.setOpis(request.getSmestaj().getOpis());
 //       smestaj.setSlike(request.getSmestaj().getSlike());
 //       List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
 //       List<smestaj.Rezervacija> rezervacijeSm = request.getSmestaj().getRezervacije();
/*
        for(smestaj.Rezervacija r : rezervacijeSm) {
            Rezervacija r1 = new Rezervacija();
            r1.setDatumDolaska(r.getDatumDolaska().toGregorianCalendar().getTime());
            r1.setDatumOdlaska(r.getDatumOdlaska().toGregorianCalendar().getTime());
            r1.setSmestajNaziv(r.getNazivSmestaja());
            r1.setSmestajId(r.getIdSmestaja());
            r1.setKorisnikId(r.getIdKorisnika());
            r1.setAgent(korisnikService.findOne(r.getAgent().getId()));
            r1.setIzvrsena(r.isIzvrseno());
            r1.setCena(r.getCena());
            rezervacijaService.create(r1);
            rezervacije.add(r1);
        }
*/
//        smestaj.setRezervacije(rezervacije);

//        List<Zauzetost> zauzetostSm = request.getSmestaj().getZauzeto();
 //       List<xmlProjectSpringbootstarter.smestaj.Zauzetost> zauzetost = new ArrayList<xmlProjectSpringbootstarter.smestaj.Zauzetost>();
/*
        for(Zauzetost z : zauzetostSm) {
            xmlProjectSpringbootstarter.smestaj.Zauzetost z1 = new xmlProjectSpringbootstarter.smestaj.Zauzetost();
            z1.setDat_kraj(z.getDatumKraja().toGregorianCalendar().getTime());
            z1.setDat_pocetak(z.getDatumPocetka().toGregorianCalendar().getTime());
            zauzetost.add(z1);
        }
*/
 //       smestaj.setZauzeto(zauzetost);

        List<Komentari> komentari = new ArrayList<Komentari>();
//        List<Komentar> komentariSm = request.getSmestaj().getKomentari();
/*
        for(Komentar k : komentariSm) {
            Komentari k1 = new Komentari();
            k1.setSadrzaj(k.getSadrzaj());
            k1.setIme(k.getIme());
            k1.setSmestajId(k.getIdSmestaja());
            k1.setOdobren(k.isOdobren());
            komentariService.create(k1);
            komentari.add(k1);

        }
*/
 //       smestaj.setKomentari(komentari);

 //       smestaj.setAgent(request.getSmestaj().getAgent().getId());
//        smestaj.setTipSmestaja(request.getSmestaj().getTipSmestaja().getId());
//        smestaj.setKategorijaSmestaja(request.getSmestaj().getKategorijaSmestaja().getId());

//        List<Termin> termini = new ArrayList<Termin>();
//        List<smestaj.Termin> terminiSm = request.getSmestaj().getTermini();
/*
        for(smestaj.Termin t : terminiSm) {
            Termin t1 = new Termin();
            t1.setCena(t.getCena());
            t1.setMesec(t.getMesec());
            terminService.create(t1);
            termini.add(t1);

        }
*/
//        smestaj.setTermini(termini);

//        List<DodatnaUsluga> dodatne = new ArrayList<DodatnaUsluga>();
//        List<smestaj.DodatnaUsluga> dodatneSm = request.getSmestaj().getDodtaneUsluge();
/*
        for(smestaj.DodatnaUsluga d : dodatneSm) {
            dodatne.add(dodatnaUslugaService.findOne(d.getId()));
        }
*/
//        smestaj.setDodatne_usluge(dodatne);

 //       Smestaj noviSmestaj = smestajService.create(smestaj);

 //       response.setId("Ubacen novi smestaj: " + noviSmestaj.getNaziv());

        return response;

    }



}
