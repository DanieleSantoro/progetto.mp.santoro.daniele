package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Servizio;

public class DecoratorServizioConSupplemento extends DecoratorServizio {

    private final double supplemento;
    private final String descrizioneSupplemento;

    public DecoratorServizioConSupplemento(Servizio servizioDecorato, double supplemento, String descrizioneSupplemento) {
        super(servizioDecorato);
        this.supplemento = supplemento;
        this.descrizioneSupplemento = descrizioneSupplemento;
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo() + supplemento;
    }
    
    @Override
    public String getNome() {
    	return super.getNome() + " (con " + this.descrizioneSupplemento + ")";
    }

	@Override
	public void accetta(VisitorServizio visitor) {
		visitor.visitaDecoratorConSupplemento(this);
	}
}