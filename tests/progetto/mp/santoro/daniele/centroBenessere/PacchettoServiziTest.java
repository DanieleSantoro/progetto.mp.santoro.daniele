package progetto.mp.santoro.daniele.centroBenessere;

import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.utils.CentroBenessereFactory;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PacchettoServiziTest {

    private CentroBenessereFactory factory;
    private Servizio massaggio;
    private Servizio sauna;

    @Before
    public void setup() {
        factory = new CentroBenessereFactory();
        massaggio = factory.creaServizioSingolo("Massaggio", 50.0, 60);
        sauna = factory.creaServizioSingolo("Sauna", 30.0, 30);
    }

    @Test
    public void testPrezzoTotalePacchetto() {
        Servizio pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        assertThat(pacchetto.getPrezzo()).isEqualTo(80.0);
    }

    @Test
    public void testDurataTotalePacchetto() {
        Servizio pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        assertThat(pacchetto.getDurata()).isEqualTo(90);
    }

    @Test
    public void testMostraDettagliPacchettoCompleto() {
        Servizio pacchetto = factory.creaPacchettoServizi("Relax Total", Arrays.asList(massaggio, sauna));
        String dettagli = pacchetto.mostraDettagli(0, true);
        String expected =
                "- Pacchetto: Relax Total\n" +
                "  - Massaggio | Prezzo: 50.0 euro | Durata: 60 min\n" +
                "  - Sauna | Prezzo: 30.0 euro | Durata: 30 min\n" +
                "Totale Prezzo: 80.0 euro | Durata totale: 90 min";
        assertThat(dettagli).isEqualTo(expected);
    }

    @Test
    public void testPrezzoDopoAggiuntaLogicaDiServizio() {
        Servizio bagnoTurco = factory.creaServizioSingolo("Bagno Turco", 25.0, 40);
        Servizio pacchettoEsteso = factory.creaPacchettoServizi("Relax Esteso", Arrays.asList(massaggio, sauna, bagnoTurco));
        assertThat(pacchettoEsteso.getPrezzo()).isEqualTo(105.0);
    }

    @Test
    public void testPrezzoDopoRimozioneLogicaDiServizio() {
        Servizio pacchettoRidotto = factory.creaPacchettoServizi("Solo Massaggio", Collections.singletonList(massaggio));
        assertThat(pacchettoRidotto.getPrezzo()).isEqualTo(50.0);
    }

    @Test
    public void testPacchettoVuotoHaCostoZero() {
        Servizio pacchettoVuoto = factory.creaPacchettoServizi("Vuoto", Collections.emptyList());
        assertThat(pacchettoVuoto.getPrezzo()).isEqualTo(0.0);
        assertThat(pacchettoVuoto.getDurata()).isEqualTo(0);
    }

    @Test
    public void testPrenotaPacchettoNonLanciaEccezioni() {
        Servizio pacchetto = factory.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
        Utente utente = factory.creaUtente("Mario");
        pacchetto.prenota(utente);
    }

    @Test
    public void testPrezzoPacchettoConServiziDuplicati() {
        Servizio pacchettoDuplicato = factory.creaPacchettoServizi("Relax Doppio", Arrays.asList(massaggio, sauna, massaggio));
        assertThat(pacchettoDuplicato.getPrezzo()).isEqualTo(130.0);
        assertThat(pacchettoDuplicato.getDurata()).isEqualTo(150);
    }

    @Test
    public void testPacchettoAnnidatoMostraDettagli() {
        Servizio massaggioLusso = factory.creaServizioSingolo("Massaggio Lusso", 100.0, 60);
        Servizio aperitivo = factory.creaServizioSingolo("Aperitivo", 20.0, 30);
        Servizio cena = factory.creaServizioSingolo("Cena", 50.0, 90);
        Servizio degustazione = factory.creaPacchettoServizi("Degustazione", Arrays.asList(aperitivo, cena));
        Servizio notteCoppia = factory.creaPacchettoServizi("Notte di Coppia", Arrays.asList(massaggioLusso, degustazione));

        String dettagli = notteCoppia.mostraDettagli(0, true);
        String expected =
                "- Pacchetto: Notte di Coppia\n" +
                "  - Massaggio Lusso | Prezzo: 100.0 euro | Durata: 60 min\n" +
                "  - Pacchetto: Degustazione\n" +
                "    - Aperitivo | Prezzo: 20.0 euro | Durata: 30 min\n" +
                "    - Cena | Prezzo: 50.0 euro | Durata: 90 min\n" +
                "Totale Prezzo: 170.0 euro | Durata totale: 180 min";
        assertThat(dettagli).isEqualTo(expected);
    }
}