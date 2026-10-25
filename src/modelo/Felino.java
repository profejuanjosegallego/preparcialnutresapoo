package modelo;

public abstract class Felino implements Identificable {
    public static final int EDAD_ADULTA = 3;

    private final String codigo;
    private String nombre;
    private int edad;
    private double pesoKg;
    private boolean sano;

    public Felino(String codigo, String nombre, int edad, double pesoKg, boolean sano) {
        // Ejemplo de validación: así se rechaza un dato inválido.
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        this.codigo = codigo;
        setNombre(nombre);
        setEdad(edad);
        setPesoKg(pesoKg);
        setSano(sano);
    }

    @Override
    public String getCodigo() { return codigo; }
    public string getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public double getPesoKg() { return pesoKg; }
    public boolean isSano() { return sano; }

    public void setNombre(String nombre) {
        // TODO (Fase 3): no permitir nombres null, vacíos o solo con espacios.
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        // TODO (Fase 3): no permitir edades negativas.
        this.edad = edad;
    }

    public void setPesoKg(double pesoKg) {
        // TODO (Fase 3): el peso debe ser mayor que 0.
        pesoKg = this.pesoKg;
    }

    public void setSano(boolean sano) {
        this.sano = sano;
    }

    /** Aumenta la edad en un año de forma controlada. */
    public void cumplirAnios() {
        setEdad(edad + 1);
    }

    /** Un felino es adulto a partir de EDAD_ADULTA años (inclusive). */
    public boolean esAdulto() {
        return edad > EDAD_ADULTA;
    }

    public abstract void hacerSonido();

    /** Kilogramos de carne que el felino debe comer al día. */
    public abstract double calcularRacionDiaria() {
        return 0;
    }

    @Override
    public String obtenerFicha() {
        return getClass().getSimpleName() + " [" + codigo + "] " + nombre
                + " - " + edad " años, " + pesoKg + " kg, "
                + (sano ? "sano" : "enfermo");
    }
}
