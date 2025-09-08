package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Utente;

public class ScontoPercentuale implements CalcoloPrezzoStrategy {

	private final double percentuale;

	public ScontoPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}

	@Override
	public double calcolaPrezzo(double prezzoBase, Utente utente) {
		return prezzoBase * (1 - percentuale);
	}

}