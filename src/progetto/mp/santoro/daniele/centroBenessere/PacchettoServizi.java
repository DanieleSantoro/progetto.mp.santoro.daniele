package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Collection;
import progetto.mp.santoro.daniele.utils.VisitorServizio;

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

    @Override
    public void accetta(VisitorServizio visitor) {
        visitor.visitaPacchettoServizi(this);
    }
}