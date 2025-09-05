package progetto.mp.santoro.daniele;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ServizioSingoloTest {

    private ServizioSingolo massaggio;
    private Collection<Prenotazione> prenotazioni;
    private Utente utente;
    private PrenotazioneFactory factory;
    
    @Before
    public void setup() {
        prenotazioni = new ArrayList<>();
        massaggio = new ServizioSingolo("Massaggio", 50.0, 60, prenotazioni, new PrenotazioneFactory());
        utente = new Utente("Mario", new ArrayList<>(), new ScontoPercentuale(0.0));
        factory = new PrenotazioneFactory();
        
    }
    
    private ServizioSingolo creaServizio(String nome, double prezzo, int durata, Collection<Prenotazione> lista, PrenotazioneFactory factory) {
        return new ServizioSingolo(nome, prezzo, durata, lista, factory);
    }

    @Test
    public void testAggiuntaPrenotazioneSingola() {
        massaggio.prenota(utente);        
        assertThat(prenotazioni)
        	.hasSize(1)
        	.allMatch(p -> p.toString().contains("Massaggio | Prezzo: 50.0 euro | Durata: 60 min"));
    }

    @Test
    public void testAggiuntaPrenotazioneMultipla() {
    	massaggio.prenota(utente);
        massaggio.prenota(utente);

        assertThat(prenotazioni)
                .hasSize(2)
                .allMatch(p -> p.toString().contains("Massaggio"));
    }

    @Test
    public void testServizioConNomeVuoto() {
    	Collection<Prenotazione> lista = new ArrayList<>();
    	ServizioSingolo senzaNome = creaServizio("", 30.0, 30, lista, factory);
        senzaNome.prenota(utente);
        
        assertThat(lista)
        	.hasSize(1)
        	.allMatch(p -> p.toString().contains(" | Prezzo: 30.0 euro | Durata: 30 min"));
    }
    
    @Test
    public void testCollezioneEsternaAggiornata() {
    	Collection<Prenotazione> listaEsterna = new ArrayList<>();
    	ServizioSingolo spa = creaServizio("Spa", 40.0, 50, listaEsterna, factory);
        spa.prenota(utente);
        
        assertThat(listaEsterna)
            .hasSize(1)
            .extracting(p -> p.getServizio().mostraDettagli())
            .containsExactly("Spa | Prezzo: 40.0 euro | Durata: 50 min");
    }
    
    @Test
    public void testServizioCostoNullo() {
        Collection<Prenotazione> lista = new ArrayList<>();
        ServizioSingolo servizioGratuito = creaServizio("Gratuito", 0.0, 10, lista, factory);
        servizioGratuito.prenota(utente);
        assertThat(lista)
        	.extracting(p -> p.getServizio().mostraDettagli())
            .containsExactly("Gratuito | Prezzo: 0.0 euro | Durata: 10 min");
    }
    
    @Test
    public void testServizioDurataZero() {
        Collection<Prenotazione> lista = new ArrayList<>();
        ServizioSingolo servizio = creaServizio("Prova", 10.0, 0, lista, factory);
        servizio.prenota(utente);
        assertThat(lista)
        	.extracting(p -> p.getServizio().mostraDettagli())
            .contains("Prova | Prezzo: 10.0 euro | Durata: 0 min");
    }

    @Test
    public void testPrenotazioneMultipleContenutoEsatto() {
        massaggio.prenota(utente);
        massaggio.prenota(utente);
        assertThat(prenotazioni)
        	.extracting(Prenotazione::getServizio)
        	.extracting(Servizio::mostraDettagli)
        	.containsExactly(
        			"Massaggio | Prezzo: 50.0 euro | Durata: 60 min",
        			"Massaggio | Prezzo: 50.0 euro | Durata: 60 min"
        	);
    }
    
}
