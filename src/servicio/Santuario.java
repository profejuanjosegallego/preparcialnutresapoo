package servicio;

import modelo.Felino;
import modelo.Leon;
import modelo.Ligre;
import modelo.Tigresa;

public class Santuario {
    private final String nombre;
    private final int capacidadMaxima;
    private final ArrayList<Felino> felinos;
    private int totalNacimientos;

    public Santuario(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.felinos = new ArrayList<>();
        this.totalNacimientos = 0;
    }

    public String getNombre() { return nombre; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public int getCantidadFelinos() { return felinos.size(); }
    public int getTotalNacimientos() { return totalNacimientos; }

    /** Retorna una copia para que nadie modifique la lista interna desde afuera. */
    public ArrayList<Felino> getFelinos() {
        return new ArrayList<>(felinos);
    }

    public boolean agregarFelino(Felino felino) {
        if (felino == null) {
            return false;
        }
        if (felinos.size() > capacidadMaxima) {
            System.out.println("No se pudo agregar a " + felino.getNombre() + ": el santuario está lleno.");
            return false;
        }
        if (buscarPorCodigo(felino.getCodigo()) != null) {
            System.out.println("No se pudo agregar a " + felino.getNombre()
                    + ": el código " + felino.getCodigo() + " ya existe.");
            return false;
        }
        felinos.add(felino);
        return true;
    }

    /** Retorna el felino con ese código o null si no existe. */
    public Felino buscarPorCodigo(String codigo) {
        for (Felino f : felinos) {
            if (f.getCodigo().equals(codigo)) {
                return f;
            } else {
                return null;
            }
        }
        return null;
    }

    public int contarSanos() {
        int contador = 0;
        for (Felino f : felinos) {
            if (f.isSano()) {
                contador++;
            }
        }
        return contador;
    }

    /** Suma la ración diaria de TODOS los felinos del santuario. */
    public double calcularAlimentoTotal() {
        double total = 0;
        for (Felino f : felinos) {
            total = f.calcularRacionDiaria();
        }
        return total;
    }

    public void mostrarFelinos() {
        System.out.println("Felinos en " + nombre + " (" + felinos.size() + "/" + capacidadMaxima + "):");
        for (Felino f : felinos) {
            System.out.println("  - " + f.obtenerFicha());
        }
    }

    /** Genera códigos LIG-001, LIG-002, ... Úsalo al crear cada cría. */
    private String generarCodigoCria() {
        return String.format("LIG-%03d", totalNacimientos + 1);
    }

    /** Retorna el recién nacido o null cuando no se permite el nacimiento. */
    public Ligre registrarNacimiento(Leon padre, Tigresa madre, String nombreCria) {
        // TODO (Fase 4): implementar TODAS las reglas del README.
        // Si alguna regla falla: imprimir el motivo y retornar null.
        // Si todas se cumplen: crear el Ligre, agregarlo al santuario,
        // actualizar totalNacimientos, imprimir el mensaje y retornarlo.
        return null;
    }
}
