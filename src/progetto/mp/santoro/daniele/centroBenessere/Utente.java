package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Collection;
import java.util.Objects;

public class Utente {

    private final String nome;
    private final Collection<Servizio> serviziPrenotati;

    public Utente(String nome, Collection<Servizio> serviziPrenotati) {
        this.nome = nome;
        this.serviziPrenotati = serviziPrenotati;
    }

    public void prenotaServizio(Servizio servizio) {
        this.serviziPrenotati.add(servizio);
    }

    public int getNumeroServiziPrenotati() {
        return serviziPrenotati.size();
    }

    public String getNome() {
        return nome;
    }
    
    public double getPrezzoTotalePrenotazioni() {
    	return serviziPrenotati.stream().mapToDouble(Servizio::getPrezzo).sum();
    }
    
    public int getDurataTotalePrenotazioni() {
    	return serviziPrenotati.stream().mapToInt(Servizio::getDurata).sum();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) obj;
        return Objects.equals(nome, utente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
