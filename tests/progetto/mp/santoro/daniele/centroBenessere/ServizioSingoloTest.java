package progetto.mp.santoro.daniele.centroBenessere;

import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.utils.CentroBenessereFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ServizioSingoloTest {

    private CentroBenessereFactory factory;
    private Utente utente;

    @Before
    public void setup() {
        factory = new CentroBenessereFactory();
        utente = factory.creaUtente("Mario");
    }

    @Test
    public void testPrenotaServizioNonLanciaEccezioni() {
        Servizio massaggio = factory.creaServizioSingolo("Massaggio", 50.0, 60);
        massaggio.prenota(utente);
        massaggio.prenota(utente);
    }

    @Test
    public void testMostraDettagliRifletteStatoCorretto() {
        Servizio massaggio = factory.creaServizioSingolo("Massaggio Speciale", 75.5, 45);
        assertThat(massaggio.mostraDettagli(0, false))
            .isEqualTo("- Massaggio Speciale | Prezzo: 75.5 euro | Durata: 45 min");
    }

    @Test
    public void testMostraDettagliConNomeVuoto() {
        Servizio senzaNome = factory.creaServizioSingolo("", 30.0, 30);
        assertThat(senzaNome.mostraDettagli(0, false))
            .isEqualTo("-  | Prezzo: 30.0 euro | Durata: 30 min");
    }

    @Test
    public void testMostraDettagliConCostoNullo() {
        Servizio servizioGratuito = factory.creaServizioSingolo("Omaggio", 0.0, 10);
        assertThat(servizioGratuito.mostraDettagli(0, false))
            .isEqualTo("- Omaggio | Prezzo: 0.0 euro | Durata: 10 min");
    }

    @Test
    public void testMostraDettagliConDurataZero() {
        Servizio servizioIstantaneo = factory.creaServizioSingolo("Check-in", 10.0, 0);
        assertThat(servizioIstantaneo.mostraDettagli(0, false))
            .isEqualTo("- Check-in | Prezzo: 10.0 euro | Durata: 0 min");
    }
}