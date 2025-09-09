package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Objects;
import progetto.mp.santoro.daniele.utils.VisitorServizio;

public class ServizioSingolo implements Servizio {

    private final String nome;
    private final double prezzo;
    private final int durata;

    public ServizioSingolo(String nome, double prezzo, int durata) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.durata = durata;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public int getDurata() {
        return durata;
    }

    @Override
    public void accetta(VisitorServizio visitor) {
        visitor.visitaServizioSingolo(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServizioSingolo that = (ServizioSingolo) o;
        return Double.compare(that.prezzo, prezzo) == 0 &&
               durata == that.durata &&
               Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, prezzo, durata);
    }
}