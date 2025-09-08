package progetto.mp.santoro.daniele.utils;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.santoro.daniele.centroBenessere.Servizio;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class VisitorTest {

	private CentroBenessereFactory factory;
    private Servizio pacchetto;

    @Before
    public void setup() {
        factory = new CentroBenessereFactory();
        Servizio massaggio = factory.creaServizioSingolo("Massaggio", 50.0, 60);
        Servizio sauna = factory.creaServizioSingolo("Sauna", 30.0, 30);
        pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
    }

    @Test
    public void testReportVisitorGeneraReportDettagliato() {
        StringBuilder report = new StringBuilder();
        ReportVisitor visitor = new ReportVisitor(report);

        pacchetto.accetta(visitor);
        String result = visitor.getReport();

        String expected =
                "Pacchetto: Relax\n" +
                "- Massaggio | Prezzo: 50.0 euro | Durata: 60 min\n" +
                "- Sauna | Prezzo: 30.0 euro | Durata: 30 min\n" +
                "Totale: 80.0 euro | Durata: 90 min\n";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testVisitorTitoliGeneraElencoNomi() {
        StringBuilder titoli = new StringBuilder();
        VisitorTitoli visitor = new VisitorTitoli(titoli);

        pacchetto.accetta(visitor);
        String result = visitor.getTitoli();

        String expected = 
                "[Pacchetto: Relax]\n" +
                "- Massaggio\n" +
                "- Sauna\n";
        assertThat(result).isEqualTo(expected);
    }
    
    @Test
    public void testReportVisitorSuPacchettoAnnidato() {
    	Servizio aperitivo = factory.creaServizioSingolo("Aperitivo", 20.0, 30);
    	Servizio pacchettoAnnidato = factory.creaPacchettoServizi("Benessere Premium", Arrays.asList(pacchetto, aperitivo));
    	
    	StringBuilder report = new StringBuilder();
    	ReportVisitor visitor = new ReportVisitor(report);
    	pacchettoAnnidato.accetta(visitor);
    	String result = visitor.getReport();
    	
    	String expected =
                "Pacchetto: Benessere Premium\n" +
                "Pacchetto: Relax\n" +
                "- Massaggio | Prezzo: 50.0 euro | Durata: 60 min\n" +
                "- Sauna | Prezzo: 30.0 euro | Durata: 30 min\n" +
                "Totale: 80.0 euro | Durata: 90 min\n" +
                "- Aperitivo | Prezzo: 20.0 euro | Durata: 30 min\n" +
                "Totale: 100.0 euro | Durata: 120 min\n";
        assertThat(result).isEqualTo(expected);
    }
}
