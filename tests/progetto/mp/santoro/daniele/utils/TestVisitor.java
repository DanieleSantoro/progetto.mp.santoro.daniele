package progetto.mp.santoro.daniele.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.centroBenessere.Servizio;

public class TestVisitor {
    private Servizio massaggio;
    private Servizio pacchettoRelax;

    @Before
    public void setup() {
        massaggio = FactoryCentroBenessere.creaServizioSingolo("Massaggio", 50.0, 60);
        Servizio sauna = FactoryCentroBenessere.creaServizioSingolo("Sauna", 30.0, 30);
        pacchettoRelax = FactoryCentroBenessere.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
    }

    @Test
    public void testVisitorReportVerificaStatoServizioSingolo() {
        VisitorReport visitor = new VisitorReport();
        massaggio.accetta(visitor);

        String expected = "- Massaggio | Prezzo: 50.0 euro | Durata: 60 min\n";
        assertThat(visitor.getReport()).isEqualTo(expected);
    }
    
    @Test
    public void testVisitorReportSuPacchetto() {
        VisitorReport visitor = new VisitorReport();
        pacchettoRelax.accetta(visitor);

        String expected =
                "- Pacchetto: Relax\n" +
                "  - Massaggio | Prezzo: 50.0 euro | Durata: 60 min\n" +
                "  - Sauna | Prezzo: 30.0 euro | Durata: 30 min\n" +
                "Totale Pacchetto: 80.0 euro | Durata: 90 min\n";
        
        assertThat(visitor.getReport()).isEqualTo(expected);
    }
    
    @Test
    public void testVisitorTitoliSuStrutturaComplessa() {
        Servizio aperitivo = FactoryCentroBenessere.creaServizioSingolo("Aperitivo", 20.0, 30);
        Servizio pacchettoPremium = FactoryCentroBenessere.creaPacchettoServizi("Premium", Arrays.asList(pacchettoRelax, aperitivo));
        
        VisitorTitoli visitor = new VisitorTitoli();
        pacchettoPremium.accetta(visitor);

        String expected = 
                "[Pacchetto: Premium]\n" +
                "[Pacchetto: Relax]\n" +
                "- Massaggio\n" +
                "- Sauna\n" +
                "- Aperitivo\n";
        assertThat(visitor.getTitoli()).isEqualTo(expected);
    }
    
    @Test
    public void testVisitorReportSuServizioDecoratoMostraStatoCorrente() {
        Servizio massaggioScontato = new DecoratorServizioScontato(massaggio, 0.10); 
        
        VisitorReport visitor = new VisitorReport();
        massaggioScontato.accetta(visitor);
        
        String expected = "- Massaggio | Prezzo: 45.0 euro | Durata: 60 min\n";
        assertThat(visitor.getReport()).isEqualTo(expected);
    }
    
}