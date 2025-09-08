package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Servizio;
import progetto.mp.santoro.daniele.centroBenessere.Utente;

public class ScontoServizi implements CalcoloPrezzoStrategy {
	
	private final int sogliaServizi;
	private final Servizio servizioBonus;

	public ScontoServizi(int sogliaServizi, Servizio servizioBonus) {
		this.sogliaServizi = sogliaServizi;
		this.servizioBonus = servizioBonus;
	}

	@Override
	public double calcolaPrezzo(double prezzoBase, Utente utente) {
		return utente.getNumeroServizi() >= sogliaServizi ? prezzoBase - servizioBonus.getPrezzo() : prezzoBase;
	}

}