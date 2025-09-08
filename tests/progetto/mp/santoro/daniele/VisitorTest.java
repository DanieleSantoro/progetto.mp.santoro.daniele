package progetto.mp.santoro.daniele;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class VisitorTest {

    private ServizioSingolo massaggio;
    private ServizioSingolo sauna;
    private PacchettoServizi pacchetto;
    private PrenotazioneFactory factory;

    @Before
    public void setup() {
        factory = new PrenotazioneFactory();
        massaggio = new ServizioSingolo("Massaggio", 50.0, 60, new ArrayList<>(), factory);
        sauna = new ServizioSingolo("Sauna", 30.0, 30, new ArrayList<>(), factory);
        pacchetto = new PacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
    }

    @Test
    public void testReportVisitorGeneraReportDettagliato() {
        StringBuilder report = new StringBuilder();
        ReportVisitor visitor = new ReportVisitor(report);

        pacchetto.accetta(visitor);
        String result = visitor.getReport();

        assertThat(result).contains("Pacchetto: Relax");
        assertThat(result).contains("Massaggio | Prezzo: 50.0 euro | Durata: 60 min");
        assertThat(result).contains("Sauna | Prezzo: 30.0 euro | Durata: 30 min");
        assertThat(result).contains("Totale: 80.0 euro");
    }

    @Test
    public void testVisitorTitoliGeneraElencoNomi() {
        StringBuilder titoli = new StringBuilder();
        VisitorTitoli visitor = new VisitorTitoli(titoli);

        pacchetto.accetta(visitor);
        String result = visitor.getTitoli();

        assertThat(result).contains("[Pacchetto: Relax]");
        assertThat(result).contains("- Massaggio");
        assertThat(result).contains("- Sauna");
    }
}
