package progetto.mp.santoro.daniele;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;

public class ScontoServiziTest {

    @Test
    public void testCalcolaPrezzoConScontoServizioGratis() {
        Servizio s1 = new ServizioSingolo("Massaggio", 100.0, 60, Collections.emptyList(), new PrenotazioneFactory());
        Servizio s2 = new ServizioSingolo("Sauna", 50.0, 30, Collections.emptyList(), new PrenotazioneFactory());
        Servizio bonus = new ServizioSingolo("BagnoTurco", 25.0, 30, Collections.emptyList(), new PrenotazioneFactory());

        CalcoloPrezzoStrategy scontoServizi = new ScontoServizi(2, bonus);
        Utente utente = new Utente("Mario", Arrays.asList(s1, s2), scontoServizi);

        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(scontoServizi);
        double totale = calcolatrice.calcolaPrezzo(s1, utente) + calcolatrice.calcolaPrezzo(s2, utente);

        assertThat(totale).isEqualTo(100.0); // include servizio bonus
    }

    @Test
    public void testCalcolaPrezzoSenzaScontoServizio() {
        Servizio s1 = new ServizioSingolo("Massaggio", 100.0, 60, Collections.emptyList(), new PrenotazioneFactory());
        Servizio s2 = new ServizioSingolo("Sauna", 50.0, 30, Collections.emptyList(), new PrenotazioneFactory());
        Servizio bonus = new ServizioSingolo("BagnoTurco", 25.0, 30, Collections.emptyList(), new PrenotazioneFactory());

        CalcoloPrezzoStrategy scontoServizi = new ScontoServizi(3, bonus);
        Utente utente = new Utente("Luca", Arrays.asList(s1, s2), scontoServizi);

        CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(scontoServizi);
        double totale = calcolatrice.calcolaPrezzo(s1, utente) + calcolatrice.calcolaPrezzo(s2, utente);

        assertThat(totale).isEqualTo(150.0); // soglia non raggiunta, prezzo invariato
    }
}
