package progetto.mp.santoro.daniele;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class PacchettoServiziTest {

    private PacchettoServizi pacchetto;
    private ServizioSingolo massaggio;
    private ServizioSingolo sauna;

    @Before
    public void setup() {
        massaggio = creaServizio("Massaggio", 50.0, 60);
        sauna = creaServizio("Sauna", 30.0, 30);
        Collection<Servizio> servizi = new ArrayList<>();
        servizi.add(massaggio);
        servizi.add(sauna);
        pacchetto = new PacchettoServizi("Relax Total", servizi);
        
    }

    private ServizioSingolo creaServizio(String nome, double prezzo, int durata) {
        return new ServizioSingolo(nome, prezzo, durata, new ArrayList<>(), new PrenotazioneFactory());
    }

    @Test
    public void testMostraDettagliPacchetto() {
        String dettagli = pacchetto.mostraDettagli();

        assertThat(dettagli).contains("Pacchetto: Relax Total");
        assertThat(dettagli).contains("Massaggio | Prezzo: 50.0 euro | Durata: 60 min");
        assertThat(dettagli).contains("Sauna | Prezzo: 30.0 euro | Durata: 30 min");
        assertThat(dettagli).contains("Totale Prezzo: 80.0");
        assertThat(dettagli).contains("Durata totale: 90 min");
    }

    @Test
    public void testaggiungiServizioAggiornaPrezzo() {
        ServizioSingolo bagnoTurco = creaServizio("BagnoTurco", 25.0, 40);
        pacchetto.aggiungiServizio(bagnoTurco);
        assertThat(pacchetto.getPrezzo()).isEqualTo(105.0);
    }

    @Test
    public void testRimuoviServizioAggiornaPrezzo() {
        ServizioSingolo bagnoTurco = creaServizio("BagnoTurco", 25.0, 40);
        pacchetto.aggiungiServizio(bagnoTurco); 
        pacchetto.rimuoviServizio(bagnoTurco);
        assertThat(pacchetto.getPrezzo()).isEqualTo(80.0);
    }
    
    @Test
    public void testMostraDettagliDopoPrenotazioni() {
        Utente utente = new Utente("Mario", new ArrayList<>(), new ScontoPercentuale(0.0));

        massaggio.prenota(utente);
        sauna.prenota(utente);

        String dettagli = pacchetto.mostraDettagli();

        assertThat(dettagli).contains("Massaggio | Prezzo: 50.0 euro | Durata: 60 min");
        assertThat(dettagli).contains("Sauna | Prezzo: 30.0 euro | Durata: 30 min");
        assertThat(dettagli).contains("Totale Prezzo: 80.0 euro");
    }

    @Test
    public void testPacchettoVuoto() {
        PacchettoServizi vuoto = new PacchettoServizi("Vuoto", new ArrayList<>());

        assertThat(vuoto.getPrezzo()).isEqualTo(0.0);
        assertThat(vuoto.getDurata()).isEqualTo(0);
        assertThat(vuoto.mostraDettagli()).contains("Pacchetto: Vuoto");
    }
    
    @Test
    public void testPrenotaPacchettoAggiornaServizi() {
    	Collection<Prenotazione> prenotazioniMassaggio = new ArrayList<>();
        Collection<Prenotazione> prenotazioniSauna = new ArrayList<>();

        ServizioSingolo massaggio2 = new ServizioSingolo("Massaggio", 50.0, 60, prenotazioniMassaggio, new PrenotazioneFactory());
        ServizioSingolo sauna2 = new ServizioSingolo("Sauna", 30.0, 30, prenotazioniSauna, new PrenotazioneFactory());

        Collection<Servizio> servizi = new ArrayList<>();
        servizi.add(massaggio2);
        servizi.add(sauna2);

        PacchettoServizi pacchetto2 = new PacchettoServizi("Relax Total", servizi);
        Utente utente = new Utente("Mario", new ArrayList<>(), new ScontoPercentuale(0.0));

        pacchetto2.prenota(utente);

        assertThat(prenotazioniMassaggio).hasSize(1);
        assertThat(prenotazioniSauna).hasSize(1);
    }
    
    @Test
    public void servizioPrezzoZeroNonInfluiscePrezzoPacchetto() {
        ServizioSingolo gratuito = creaServizio("Gratuito", 0.0, 0);
        pacchetto.aggiungiServizio(gratuito);
        assertThat(pacchetto.getPrezzo()).isEqualTo(80.0); // prezzo totale rimane invariato
    }

    @Test
    public void servizioDurataZeroNonInfluisceDurataPacchetto() {
        ServizioSingolo gratuito = creaServizio("Gratuito", 0.0, 0);
        pacchetto.aggiungiServizio(gratuito);
        assertThat(pacchetto.getDurata()).isEqualTo(90); // durata totale rimane invariata
    }

    @Test
    public void testDuplicatiServizi() {
        pacchetto.aggiungiServizio(massaggio); // aggiungo un duplicato
        assertThat(pacchetto.getPrezzo()).isEqualTo(130.0); // 50 + 30 + 50
        assertThat(pacchetto.getDurata()).isEqualTo(150);   // 60 + 30 + 60
    }

    
    
    
}
