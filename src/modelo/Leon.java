package modelo;

public class Leon extends Felino {
    private final boolean macho;
    private final boolean tieneMelena;

    public Leon(String codigo, String nombre, int edad, double pesoKg,
                boolean sano, boolean macho, boolean tieneMelena) {
        super(codigo, nombre, edad, pesoKg, sano)
        this.macho = macho;
        this.tieneMelena = tieneMelena;
    }

    public boolean isMacho() { return macho; }
    public boolean isTieneMelena() { return tieneMelena; }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + ": ¡ROAAAR!");
    }

    /** Un león come el 5 % de su peso al día. */
    @Override
    public int calcularRacionDiaria() {
        return getPesoKg() * 0.05;
    }
}
