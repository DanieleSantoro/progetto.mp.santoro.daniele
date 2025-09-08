package progetto.mp.santoro.daniele;

public class CalcolatricePrezzo {

	private CalcoloPrezzoStrategy strategia;
	
	public CalcolatricePrezzo(CalcoloPrezzoStrategy strategia) {
		this.strategia = strategia;
	}
	
	public double calcolaPrezzo(Servizio servizio, Utente utente) {
		return strategia.calcolaPrezzo(servizio.getPrezzo(), utente);
	}
	
	public double calcolaPrezzo(PacchettoServizi pacchetto, Utente utente) {
		double prezzoBase = pacchetto.getServizi().stream()
									 .mapToDouble(Servizio::getPrezzo)
									 .sum();
		return strategia.calcolaPrezzo(prezzoBase, utente);
	}
		
}
