package progetto.mp.santoro.daniele.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import progetto.mp.santoro.daniele.centroBenessere.Servizio;

public class TestDecorator {

	private Servizio massaggio;

	@Before
	public void setup() {
		massaggio = FactoryCentroBenessere.creaServizioSingolo("Massaggio", 100.0, 60);
	}

	@Test
	public void testComportamentoDecoratorSconto() {
		Servizio massaggioScontato = new DecoratorServizioScontato(massaggio, 0.20);

		assertThat(massaggioScontato.getPrezzo()).isEqualTo(80.0);
	}

	@Test
	public void testComportamentoDecoratorSupplemento() {
		Servizio massaggioConOlio = new DecoratorServizioConSupplemento(massaggio, 15.0, "Oli Essenziali");

		assertThat(massaggioConOlio.getPrezzo()).isEqualTo(115.0);
	}

	@Test
	public void testComportamentoDecoratoriAnnidati() {
		Servizio decorato = new DecoratorServizioConSupplemento(massaggio, 20.0, "Kit Lusso");
		decorato = new DecoratorServizioScontato(decorato, 0.10);

		assertThat(decorato.getPrezzo()).isEqualTo(108.0);
	}

	@Test
	public void testComportamentoDecoratorSuPacchetto() {
		Servizio sauna = FactoryCentroBenessere.creaServizioSingolo("Sauna", 50.0, 30);
		Servizio pacchetto = FactoryCentroBenessere.creaPacchettoServizi("Relax", Arrays.asList(massaggio, sauna));
		Servizio pacchettoScontato = new DecoratorServizioScontato(pacchetto, 0.50);

		assertThat(pacchettoScontato.getPrezzo()).isEqualTo(75.0);
	}
	
	@Test
    public void testDecoratoriMultipliDelloStessoTipo() {
        Servizio massaggioConDoppioSconto = new DecoratorServizioScontato(massaggio, 0.20); // Prezzo diventa 80.0
        massaggioConDoppioSconto = new DecoratorServizioScontato(massaggioConDoppioSconto, 0.50); // Prezzo diventa 40.0
        
        assertThat(massaggioConDoppioSconto.getPrezzo()).isEqualTo(40.0);
    }
	
	 @Test
	 public void testDecoratorSupplementoModificaNomeEPrezzo() {
		 DecoratorServizioConSupplemento massaggioConOlio = new DecoratorServizioConSupplemento(massaggio, 15.0, "Oli Essenziali");

		 assertThat(massaggioConOlio.getPrezzo()).isEqualTo(115.0);
		 assertThat(massaggioConOlio.getNome()).isEqualTo("Massaggio (con Oli Essenziali)");
	 }

}