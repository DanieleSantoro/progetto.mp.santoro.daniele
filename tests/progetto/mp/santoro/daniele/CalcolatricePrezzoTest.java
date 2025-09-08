package progetto.mp.santoro.daniele;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcolatricePrezzoTest {

    private CalcolatricePrezzo calcolatricePercentuale;
    private CalcolatricePrezzo calcolatriceServizi;

    private Utente utentePercentuale;
    private Utente utenteServizi;

    private ServizioSingolo massaggio;
    private ServizioSingolo sauna;
    private ServizioSingolo bonus;

    @Before
    public void setup() {
        massaggio = new ServizioSingolo("Massaggio", 100.0, 60, new ArrayList<>(), new PrenotazioneFactory());
        sauna = new ServizioSingolo("Sauna", 50.0, 30, new ArrayList<>(), new PrenotazioneFactory());
        bonus = new ServizioSingolo("BagnoTurco", 25.0, 30, new ArrayList<>(), new PrenotazioneFactory());

        calcolatricePercentuale = new CalcolatricePrezzo(new ScontoPercentuale(0.2));
        utentePercentuale = new Utente("Mario", Arrays.asList(massaggio, sauna), new ScontoPercentuale(0.2));

        calcolatriceServizi = new CalcolatricePrezzo(new ScontoServizi(2, bonus));
        utenteServizi = new Utente("Luca", Arrays.asList(massaggio, sauna), new ScontoServizi(2, bonus));
    }

    @Test
    public void testCalcolaPrezzoConScontoPercentuale() {
        PacchettoServizi pacchetto = new PacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        double totale = calcolatricePercentuale.calcolaPrezzo(pacchetto, utentePercentuale);

        assertThat(totale).isEqualTo(150.0 * 0.8);
    }

    @Test
    public void testCalcolaPrezzoConScontoServizi() {
        PacchettoServizi pacchetto = new PacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        double totale = calcolatriceServizi.calcolaPrezzo(pacchetto, utenteServizi);

        assertThat(totale).isEqualTo(125.0); // applica servizio bonus
    }

    @Test
    public void testCalcolaPrezzoSingoloServizio() {
        double prezzo = calcolatricePercentuale.calcolaPrezzo(massaggio, utentePercentuale);
        assertThat(prezzo).isEqualTo(100.0 * 0.8);
    }

    @Test
    public void testCalcolaPrezzoPacchettoVuoto() {
        PacchettoServizi vuoto = new PacchettoServizi("Vuoto", new ArrayList<>());
        double prezzo = calcolatricePercentuale.calcolaPrezzo(vuoto, utentePercentuale);
        assertThat(prezzo).isEqualTo(0.0);
    }
}
