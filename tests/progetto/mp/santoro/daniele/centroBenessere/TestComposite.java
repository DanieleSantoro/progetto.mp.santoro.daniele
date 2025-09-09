package progetto.mp.santoro.daniele.centroBenessere;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

import progetto.mp.santoro.daniele.utils.DecoratorServizioScontato;
import progetto.mp.santoro.daniele.utils.FactoryCentroBenessere;

public class TestComposite {

    private Servizio massaggio;
    private Servizio sauna;

    @Before
    public void setup() {
        massaggio = FactoryCentroBenessere.creaServizioSingolo("Massaggio", 50.0, 60);
        sauna = FactoryCentroBenessere.creaServizioSingolo("Sauna", 30.0, 30);
    }

    @Test
    public void testPrezzoDurataPacchettoSemplice() {
        Servizio pacchetto = FactoryCentroBenessere.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        
        assertThat(pacchetto.getPrezzo()).isEqualTo(80.0);
        assertThat(pacchetto.getDurata()).isEqualTo(90);
    }
    
    @Test
    public void testPrezzoDurataPacchettoConPiuServizi() {
        Servizio bagnoTurco = FactoryCentroBenessere.creaServizioSingolo("Bagno Turco", 25.0, 40);
        Servizio pacchettoEsteso = FactoryCentroBenessere.creaPacchettoServizi("Relax Esteso", Arrays.asList(massaggio, sauna, bagnoTurco));
        
        assertThat(pacchettoEsteso.getPrezzo()).isEqualTo(105.0);
    }

    @Test
    public void testPacchettoConUnSoloServizio() {
        Servizio pacchettoRidotto = FactoryCentroBenessere.creaPacchettoServizi("Solo Massaggio", Collections.singletonList(massaggio));
        
        assertThat(pacchettoRidotto.getPrezzo()).isEqualTo(50.0);
    }
    
    @Test
    public void testPacchettoVuotoHaComportamentoNullo() {
        Servizio pacchettoVuoto = FactoryCentroBenessere.creaPacchettoServizi("Vuoto", Collections.emptyList());
        
        assertThat(pacchettoVuoto.getPrezzo()).isZero();
        assertThat(pacchettoVuoto.getDurata()).isZero();
    }
    
    @Test
    public void testPrezzoPacchettoConServiziDuplicati() {
        Servizio pacchettoDuplicato = FactoryCentroBenessere.creaPacchettoServizi("Relax Doppio", Arrays.asList(massaggio, sauna, massaggio));
        
        assertThat(pacchettoDuplicato.getPrezzo()).isEqualTo(130.0);
        assertThat(pacchettoDuplicato.getDurata()).isEqualTo(150);
    }

    @Test
    public void testPrezzoDurataPacchettoAnnidato() {
        Servizio pacchettoRelax = FactoryCentroBenessere.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        Servizio pacchettoAnnidato = FactoryCentroBenessere.creaPacchettoServizi("Relax con Extra", Arrays.asList(pacchettoRelax));
        
        assertThat(pacchettoAnnidato.getPrezzo()).isEqualTo(80.0);
        assertThat(pacchettoAnnidato.getDurata()).isEqualTo(90);
    }
    
    @Test
    public void testPrezzoPacchettoConServizioInternoDecorato() {
        Servizio massaggioScontato = new DecoratorServizioScontato(massaggio, 0.50); // Prezzo: 25.0
        Servizio pacchetto = FactoryCentroBenessere.creaPacchettoServizi("Relax Scontato", Arrays.asList(massaggioScontato, sauna));
        
        assertThat(pacchetto.getPrezzo()).isEqualTo(55.0);
    }
    
    
}