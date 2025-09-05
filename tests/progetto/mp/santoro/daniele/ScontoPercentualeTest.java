package progetto.mp.santoro.daniele;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ScontoPercentualeTest {

    @Test
    public void testCalcolaPrezzoConSconto20Percento() {
        Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, Arrays.asList(), new PrenotazioneFactory());
        Servizio sauna = new ServizioSingolo("Sauna", 50.0, 30, Arrays.asList(), new PrenotazioneFactory());

        CalcoloPrezzoStrategy scontoPercentuale = new ScontoPercentuale(0.2);

        Utente utente = new Utente("Mario",
                Arrays.asList(massaggio, sauna),
                scontoPercentuale);

        double totale = utente.calcolaPrezzoTotale();

        assertThat(totale).isEqualTo((100 + 50) * 0.8);
    }

    @Test
    public void calcolaPrezzoConSconto0Percento() {
        Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, Arrays.asList(), new PrenotazioneFactory());

        CalcoloPrezzoStrategy scontoPercentualeNullo = new ScontoPercentuale(0.0);

        Utente utente = new Utente("Luca",
                Arrays.asList(massaggio),
                scontoPercentualeNullo);

        double totale = utente.calcolaPrezzoTotale();

        assertThat(totale).isEqualTo(100.0);
    }
}
