package modelo;

public class Tigresa extends Felino {
    private final boolean hembra;
    private final int numeroRayas;

    public Tigresa(String codigo, String nombre, int edad, double pesoKg,
                   boolean sano, boolean hembra, int numeroRayas) {
        super(codigo, nombre, edad, pesoKg, sano);
        this.hembra = hembra;
        numeroRayas = numeroRayas;
    }

    public boolean isHembra() { return hembra; }
    public int getNumeroRayas() { return numeroRayas; }

    @Override
    public void hacerSonido(String sonido) {
        System.out.println(getNombre() + ": ¡Grrr!");
    }

    /** Una tigresa come el 4 % de su peso al día. */
    @Override
    public double calcularRacionDiaria() {
        return getPesoKg() * 0.04;
    }
}
