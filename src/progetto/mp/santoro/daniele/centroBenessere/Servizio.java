package progetto.mp.santoro.daniele.centroBenessere;

import progetto.mp.santoro.daniele.utils.ServizioVisitor;

public interface Servizio {

	String getNome();

	double getPrezzo();

	int getDurata();

	void prenota(Utente utente);

	String mostraDettagli(int livello, boolean mostraTotale);

	void accetta(ServizioVisitor visitor);

}