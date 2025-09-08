package progetto.mp.santoro.daniele;

import java.util.Collection;

public class PacchettoServizi implements Servizio {

	private final String nome;
	private final Collection<Servizio> servizi;

	public PacchettoServizi(String nome, Collection<Servizio> servizi) {
		this.nome = nome;
		this.servizi = servizi;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	public Collection<Servizio> getServizi() {
		return servizi;
	}
	
	@Override
	public double getPrezzo() {
		return servizi.stream().mapToDouble(Servizio::getPrezzo).sum();
	}
	
	@Override
	public int getDurata() {
		return servizi.stream().mapToInt(Servizio::getDurata).sum();
	}
	
	public void aggiungiServizio(Servizio servizio) {
		servizi.add(servizio);
	}

	public void rimuoviServizio(Servizio servizio) {
		servizi.remove(servizio);
	}

	@Override
	public void prenota(Utente utente) {		
		servizi.forEach(s -> s.prenota(utente));
	}

	@Override
    public String mostraDettagli() {
        StringBuilder sb = new StringBuilder("Pacchetto: " + nome + "\n");
        for (Servizio s : servizi) {
            sb.append(" - ").append(s.mostraDettagli()).append("\n");
        }
        sb.append("Totale Prezzo: ").append(getPrezzo())
          .append(" euro | Durata totale: ").append(getDurata()).append(" min");
        return sb.toString();
    }

	@Override
	public void accetta(ServizioVisitor visitor) {
		visitor.visitaPacchetto(this);
	}
	
	
//	@Override
//	public String mostraDettagli() {
//	    String dettagliServizi = servizi.stream()
//	        .map(s -> " - " + s.getNome() 
//	                + " | Prezzo: " + s.getPrezzo() 
//	                + " euro | Durata: " + s.getDurata() + " min")
//	        .reduce("", (a, b) -> a + "\n" + b);
//
//	    return "Pacchetto: " + nome
//	           + dettagliServizi
//	           + "\nTotale Prezzo: " + getPrezzo() 
//	           + " euro, Durata totale: " + getDurata() + " min";
//	}

//	@Override
//	public String mostraDettagli() {
//	    StringBuilder dettagliServizi = new StringBuilder();
//	    for (Servizio s : servizi) {
//	        dettagliServizi.append(" - ")
//	                       .append(s.getNome())
//	                       .append(" | Prezzo: ")
//	                       .append(s.getPrezzo())
//	                       .append(" euro | Durata: ")
//	                       .append(s.getDurata())
//	                       .append(" min\n");
//	    }
//
//	    return "Pacchetto: " + nome + "\n"
//	           + dettagliServizi
//	           + "Totale Prezzo: " + getPrezzo()
//	           + " euro, Durata totale: " + getDurata() + " min";
//	}
	
	

}
