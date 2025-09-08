package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Collection;

import progetto.mp.santoro.daniele.utils.ServizioVisitor;

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
	
	@Override
	public double getPrezzo() {
		return servizi.stream().mapToDouble(Servizio::getPrezzo).sum();
	}

	@Override
	public int getDurata() {
		return servizi.stream().mapToInt(Servizio::getDurata).sum();
	}

	@Override
	public void prenota(Utente utente) {
		servizi.forEach(s -> s.prenota(utente));
	}

	@Override
	public String mostraDettagli(int livello, boolean mostraTotale) {
		String indent = "  ".repeat(livello);
		String dettagli = indent + "- Pacchetto: " + nome;
		
		for (Servizio s : servizi) {
	        dettagli += "\n" + s.mostraDettagli(livello + 1, false);
	    }

		if (mostraTotale) {
	        dettagli += "\n" + indent + "Totale Prezzo: " + getPrezzo()
	                  + " euro | Durata totale: " + getDurata() + " min";
		}

		return dettagli;
	}
	
	public void visitaFigli(ServizioVisitor visitor) {
        for (Servizio s : this.servizi) {
            s.accetta(visitor);
        }
    }

	@Override
    public void accetta(ServizioVisitor visitor) {
		visitor.visitaPacchetto(this);
	}

}
