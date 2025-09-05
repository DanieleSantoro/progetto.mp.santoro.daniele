package progetto.mp.santoro.daniele;

public interface Servizio {

	String getNome();

	double getPrezzo();

	int getDurata();

	void prenota(Utente utente);

	String mostraDettagli();

}
