package progetto.mp.santoro.daniele.utils;

import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.centroBenessere.Servizio;
import progetto.mp.santoro.daniele.centroBenessere.Utente;

import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class CalcolatricePrezzoTest {

    private CentroBenessereFactory factory;
    private Utente utente;
    private Servizio massaggio;
    private Servizio sauna;
    private Servizio bonus;

    @Before
    public void setup() {
        factory = new CentroBenessereFactory();
        massaggio = factory.creaServizioSingolo("Massaggio", 100.0, 60);
        sauna = factory.creaServizioSingolo("Sauna", 50.0, 30);
        bonus = factory.creaServizioSingolo("BagnoTurco", 25.0, 30);
        
        utente = factory.creaUtente("Mario");
        utente.prenotaServizio(massaggio);
        utente.prenotaServizio(sauna);
    }

    @Test
    public void testCalcolaPrezzoPacchettoConScontoPercentuale() {
        CalcoloPrezzoStrategy strategia = new ScontoPercentuale(0.2);
        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(strategia);
        Servizio pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        
        double totale = calcolatrice.calcolaPrezzo(pacchetto, utente);
        assertThat(totale).isEqualTo(120.0);
    }

    @Test
    public void testCalcolaPrezzoPacchettoConScontoServizi() {
        CalcoloPrezzoStrategy strategia = new ScontoServizi(2, bonus);
        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(strategia);
        Servizio pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));

        double totale = calcolatrice.calcolaPrezzo(pacchetto, utente);
        assertThat(totale).isEqualTo(125.0);
    }

    @Test
    public void testCalcolaPrezzoServizioSingoloConScontoPercentuale() {
        CalcoloPrezzoStrategy strategia = new ScontoPercentuale(0.1);
        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(strategia);
        
        double prezzo = calcolatrice.calcolaPrezzo(massaggio, utente);
        assertThat(prezzo).isEqualTo(90.0);
    }

    @Test
    public void testCalcolaPrezzoSenzaScontoServiziSeSogliaNonRaggiunta() {
        CalcoloPrezzoStrategy strategia = new ScontoServizi(3, bonus);
        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(strategia);
        Servizio pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));

        double totale = calcolatrice.calcolaPrezzo(pacchetto, utente);
        assertThat(totale).isEqualTo(150.0);
    }
}