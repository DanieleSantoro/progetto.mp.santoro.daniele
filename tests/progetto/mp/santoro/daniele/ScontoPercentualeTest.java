package progetto.mp.santoro.daniele;

import org.junit.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class ScontoPercentualeTest {

    @Test
    public void testCalcolaPrezzoServizioSingoloConSconto() {
        Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, Arrays.asList(), new PrenotazioneFactory());
        CalcoloPrezzoStrategy sconto20 = new ScontoPercentuale(0.2);
        Utente utente = new Utente("Mario", Arrays.asList(massaggio), sconto20);

        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(sconto20);
        double totale = calcolatrice.calcolaPrezzo(massaggio, utente);

        assertThat(totale).isEqualTo(80.0);
    }

    @Test
    public void testCalcolaPrezzoPacchettoConSconto() {
        Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, Arrays.asList(), new PrenotazioneFactory());
        Servizio sauna = new ServizioSingolo("Sauna", 50.0, 30, Arrays.asList(), new PrenotazioneFactory());
        PacchettoServizi pacchetto = new PacchettoServizi("Relax Totale", Arrays.asList(massaggio, sauna));

        CalcoloPrezzoStrategy sconto20 = new ScontoPercentuale(0.2);
        Utente utente = new Utente("Daniele", Arrays.asList(massaggio, sauna), sconto20);

        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(sconto20);
        double totale = calcolatrice.calcolaPrezzo(pacchetto, utente);

        assertThat(totale).isEqualTo(120.0); // (100+50)*0.8
    }

    @Test
    public void testCalcolaPrezzoServizioConSconto0Percento() {
        Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, Arrays.asList(), new PrenotazioneFactory());
        CalcoloPrezzoStrategy sconto0 = new ScontoPercentuale(0.0);
        Utente utente = new Utente("Luca", Arrays.asList(massaggio), sconto0);

        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(sconto0);
        double totale = calcolatrice.calcolaPrezzo(massaggio, utente);

        assertThat(totale).isEqualTo(100.0);
    }
}
