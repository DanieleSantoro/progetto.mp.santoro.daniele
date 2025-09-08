package progetto.mp.santoro.daniele.centroBenessere;

import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.utils.CentroBenessereFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class UtenteTest {

    private CentroBenessereFactory factory;
    private Utente utente;

    @Before
    public void setup() {
        factory = new CentroBenessereFactory();
        utente = factory.creaUtente("Luca");
    }

    @Test
    public void testNuovoUtenteHaZeroServizi() {
        assertThat(utente.getNumeroServizi()).isEqualTo(0);
    }

    @Test
    public void testPrenotaServizioIncrementaNumeroServizi() {
        Servizio massaggio = factory.creaServizioSingolo("Massaggio", 100.0, 60);
        Servizio sauna = factory.creaServizioSingolo("Sauna", 50.0, 30);

        utente.prenotaServizio(massaggio);
        assertThat(utente.getNumeroServizi()).isEqualTo(1);

        utente.prenotaServizio(sauna);
        assertThat(utente.getNumeroServizi()).isEqualTo(2);
    }
    
    @Test
    public void testGetNome() {
        assertThat(utente.getNome()).isEqualTo("Luca");
    }
}