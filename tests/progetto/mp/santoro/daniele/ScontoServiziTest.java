package progetto.mp.santoro.daniele;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ScontoServiziTest {

    @Test
    public void calcolaPrezzoConScontoServizioGratis() {
        Servizio s1 = new ServizioSingolo("Massaggio", 100.0, 60, Collections.emptyList(), new PrenotazioneFactory());
        Servizio s2 = new ServizioSingolo("Sauna", 50.0, 30, Collections.emptyList(), new PrenotazioneFactory());
        Servizio sconto = new ServizioSingolo("BagnoTurco", 25.0, 30, Collections.emptyList(), new PrenotazioneFactory());

        CalcoloPrezzoStrategy scontoServizi = new ScontoServizi(2, sconto);

        Utente utente = new Utente("Mario",
                Arrays.asList(s1, s2),
                scontoServizi);

        double totale = utente.calcolaPrezzoTotale();

        // Perché ha >= 2 servizi, il prezzo include anche il servizio gratuito
        assertThat(totale).isEqualTo(125);
    }

    @Test
    public void calcolaPrezzoSenzaScontoServizio() {
        Servizio s1 = new ServizioSingolo("Massaggio", 100.0, 60, Collections.emptyList(), new PrenotazioneFactory());
        Servizio s2 = new ServizioSingolo("Sauna", 50.0, 30, Collections.emptyList(), new PrenotazioneFactory());
        Servizio sconto = new ServizioSingolo("BagnoTurco", 25.0, 30, Collections.emptyList(), new PrenotazioneFactory());

        CalcoloPrezzoStrategy scontoServizi = new ScontoServizi(3, sconto);

        Utente utente = new Utente("Luca",
                Arrays.asList(s1, s2),
                scontoServizi);

        double totale = utente.calcolaPrezzoTotale();

        // Non raggiunge la soglia, prezzo invariato
        assertThat(totale).isEqualTo(100 + 50);
    }
}
