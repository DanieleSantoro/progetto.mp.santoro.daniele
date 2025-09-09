package progetto.mp.santoro.daniele.centroBenessere;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.utils.FactoryCentroBenessere;

public class TestUtente {

    private Utente utente;

    @Before
    public void setup() {
        utente = FactoryCentroBenessere.creaUtente("Luca");
    }

    @Test
    public void testComportamentoCalcoloTotaliDopoPrenotazioni() {
        Servizio massaggio = FactoryCentroBenessere.creaServizioSingolo("Massaggio", 50.0, 60);
        Servizio sauna = FactoryCentroBenessere.creaServizioSingolo("Sauna", 30.0, 30);
        Servizio pacchettoRelax = FactoryCentroBenessere.creaPacchettoServizi("Relax", Arrays.asList(sauna, massaggio));
        
        utente.prenotaServizio(massaggio);
        assertThat(utente.getPrezzoTotalePrenotazioni()).isEqualTo(50.0);
        
        utente.prenotaServizio(pacchettoRelax);
        assertThat(utente.getPrezzoTotalePrenotazioni()).isEqualTo(130.0);
        assertThat(utente.getDurataTotalePrenotazioni()).isEqualTo(150);
    }
    @Test
    public void testComportamentoPrenotazioneServizio() {
        Servizio massaggio = FactoryCentroBenessere.creaServizioSingolo("Massaggio", 100.0, 60);
        Servizio sauna = FactoryCentroBenessere.creaServizioSingolo("Sauna", 50.0, 30);

        utente.prenotaServizio(massaggio);
        assertThat(utente.getNumeroServiziPrenotati()).isEqualTo(1);

        utente.prenotaServizio(sauna);
        assertThat(utente.getNumeroServiziPrenotati()).isEqualTo(2);
    }
    
    @Test
    public void testPrenotazionePacchettoVuotoNonAlteraTotali() {
        Servizio massaggio = FactoryCentroBenessere.creaServizioSingolo("Massaggio", 50.0, 60);
        Servizio pacchettoVuoto = FactoryCentroBenessere.creaPacchettoServizi("Vuoto", Collections.emptyList());

        utente.prenotaServizio(massaggio);
        double prezzoIniziale = utente.getPrezzoTotalePrenotazioni();

        utente.prenotaServizio(pacchettoVuoto);
        
        assertThat(utente.getPrezzoTotalePrenotazioni()).isEqualTo(prezzoIniziale);
    }
    
    
    
}