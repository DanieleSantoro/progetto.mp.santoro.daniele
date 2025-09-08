package progetto.mp.santoro.daniele;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class UtenteTest {

	private PrenotazioneFactory factory;

	@Before
	public void setup() {
		factory = new PrenotazioneFactory();
	}

	@Test
	public void testCalcoloPrezzoTotaleConScontoPercentuale() {
		ServizioSingolo massaggio = new ServizioSingolo("Massaggio", 100.0, 60, new ArrayList<>(), factory);
		ServizioSingolo sauna = new ServizioSingolo("Sauna", 50.0, 30, new ArrayList<>(), factory);
		Utente utente = new Utente("Mario", Arrays.asList(massaggio, sauna), new ScontoPercentuale(0.2));

		CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(new ScontoPercentuale(0.2));
		double totale = calcolatrice
				.calcolaPrezzo(new PacchettoServizi("PacchettoTest", Arrays.asList(massaggio, sauna)), utente);

		assertThat(totale).isEqualTo((100 + 50) * 0.8);
	}

	@Test
	public void testNmeroServizi() {
		Collection<Prenotazione> prenotazioniMassaggio = Arrays.asList();
		Collection<Prenotazione> prenotazioniSauna = Arrays.asList();

		Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, prenotazioniMassaggio, factory);
		Servizio sauna = new ServizioSingolo("Sauna", 50.0, 30, prenotazioniSauna, factory);

		Utente utente = new Utente("Luca", Arrays.asList(massaggio, sauna), new ScontoPercentuale(0.1));

		assertThat(utente.getNumeroServizi()).isEqualTo(2);
	}

	@Test
	public void testPrenotazioneServiziConAggiornamentoLista() {
		Collection<Prenotazione> prenotazioniMassaggio = new ArrayList<>();
		Servizio massaggio = new ServizioSingolo("Massaggio", 100.0, 60, prenotazioniMassaggio, factory);

		Utente utente = new Utente("Daniele", new ArrayList<>(), new ScontoPercentuale(0.0));

		utente.prenotaServizio(massaggio);

		assertThat(prenotazioniMassaggio).extracting(Prenotazione::getServizio).extracting(Servizio::getNome)
				.containsExactly("Massaggio");

	}
	
	@Test
	public void testCalcoloPrezzoTotaleScontoServizi() {
		ServizioSingolo massaggio = new ServizioSingolo("Massaggio", 100.0, 60, new ArrayList<>(), factory);
		ServizioSingolo sauna = new ServizioSingolo("Sauna", 100.0, 60, new ArrayList<>(), factory);
		
		ServizioSingolo bonus = new ServizioSingolo("Bonus", 25.0, 30, new ArrayList<>(), factory);
		
		
		Utente utente = new Utente("Daniele", Arrays.asList(massaggio, sauna), new ScontoServizi(2, bonus));
		CalcolatricePrezzo calcolatrice = new CalcolatricePrezzo(new ScontoServizi(2, bonus));
		
		double totale = calcolatrice.calcolaPrezzo(new PacchettoServizi("PacchettoTest", Arrays.asList(massaggio, sauna)), utente);
		
		assertThat(totale).isEqualTo(175.0);
		
	}

}
