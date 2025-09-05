package progetto.mp.santoro.daniele;

public class ScontoServizi implements CalcoloPrezzoStrategy {
	private int sogliaServizi;
	private Servizio servizioBonus;

	public ScontoServizi(int sogliaServizi, Servizio servizioBonus) {
		this.sogliaServizi = sogliaServizi;
		this.servizioBonus = servizioBonus;
	}

	@Override
	public double calcolaPrezzo(double prezzoBase, Utente utente) {
		return utente.getNumeroServizi() >= sogliaServizi ? prezzoBase - servizioBonus.getPrezzo() : prezzoBase;
	}

}