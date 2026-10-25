package modelo;

public class Ligre extends Felino {
    private final Leon padre;
    private final Tigresa madre;

    /** El ligre nace con edad 0, sano y con el 1 % de la suma del peso de sus padres. */
    public Ligre(String codigo, String nombre, Leon padre, Tigresa madre) {
        super(codigo, nombre, 0, true);
        this.padre = padre;
        this.madre = madre;
    }

    public Tigresa getPadre() { return padre; }
    public Tigresa getMadre() { return madre; }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + ": ¡Rugidito!");
    }

    /** Un ligre come el 6 % de su peso al día. */
    @Override
    public double calcularRacionDiaria() {
        return getPesoKg() * 0.06;
    }

    @Override
    public String obtenerFicha() {
        return super.obtenerFicha() + " | hijo de " + padre.getNombre()
                + " y " + madre.getNombre();
    }
}
