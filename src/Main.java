import modelo.Felino;
import modelo.Identificable;
import modelo.Leon;
import modelo.Ligre;
import modelo.Tigresa;
import servicio.Santuario;

public class Main {
    public static void main(String[] args) {
        Santuario santuario = new Santuario("Santuario POO", 4);

        //                  código     nombre  edad  peso   sano  macho melena
        Leon leo = new Leon("LEO-001", "Leo", 5, 190.0, true, true, true);
        //                           código     nombre   edad  peso   sana  hembra rayas
        Tigresa tania = new Tigresa("TIG-001", "Tania", 4, 140.0, true, true, 100);
        Leon simba = new Leon("LEO-002", "Simba", 3, 160.0, true, true, false);

        System.out.println("=== 1. Registro de felinos ===");
        santuario.agregarFelino(leo);
        santuario.agregarFelino(tania);
        santuario.agregarFelino(simba);
        Leon copia = new Leon("TIG-001", "Copia", 4, 150.0, true, true, true);
        System.out.println("¿Se aceptó un código repetido? " + santuario.agregarFelino(copia));
        santuario.mostrarFelinos();

        System.out.println("\n=== 2. Verificación de lógica ===");
        System.out.println("¿Simba (3 años) es adulto? " + simba.esAdulto());
        Felino encontrado = santuario.buscarPorCodigo("TIG-001");
        System.out.println("Buscar TIG-001: " + (encontrado != null ? encontrado.getNombre() : "no encontrado"));
        System.out.println("Felinos sanos: " + santuario.contarSanos());
        int alimento = santuario.calcularAlimentoTotal();
        System.out.printf("Alimento diario total: %.2f kg%n", alimento);

        System.out.println("\n=== 3. Polimorfismo ===");
        Felino animal = leo;
        Identificable ficha = tania;
        System.out.println("Como Felino: " + animal.obtenerFicha());
        System.out.println("Como Identificable: " + ficha.getCodigo());
        for (Felino f : santuario.getFelinos()) {
            f.hacerSonido();
        }

        System.out.println("\n=== 4. Encapsulamiento ===");
        try {
            leo.setEdad(-2);
            System.out.println("ERROR: se aceptó una edad negativa");
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazado: " + e.getMessage());
        }
        // TODO (Fase 5): probar de la misma forma un nombre vacío y un peso de 0.

        System.out.println("\n=== 5. Nacimientos ===");
        Ligre milo = santuario.registrarNacimiento(leo, tania, "Milo");
        if (milo != null) {
            System.out.println(milo.obtenerFicha());
            System.out.println("Padres: " + milo.getPadre().getNombre()
                    + " y " + milo.getMadre().getNombre());
        }
        Tigresa nala = new Tigresa("TIG-002", "Nala", 6, 130.0, true, true, 90);
        System.out.println("¿Cabe otro felino? " + santuario.agregarFelino(nala));

        // TODO (Fase 5): probar al menos TRES escenarios diferentes que NO permitan el nacimiento.

        System.out.println("\nTotal de nacimientos: " + santuario.getTotalNacimientos());
    }
}
