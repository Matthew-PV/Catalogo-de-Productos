package interfaz;

import dominio.*;

import java.io.File;
import java.util.Scanner;

public class Interfaz {
    private Catalogo catalogo;
    private static final Scanner teclado = new Scanner(System.in);


    //Constructores
    public Interfaz() {}


    //Métodos de Interfaz
    public void ejecutar() {
        System.out.println("¡Bienvenido/a al catálogo de zapatillas! " +
                "¿Cómo se llama el catálogo donde quieres trabajar?");
        String nombreCatalogo = teclado.nextLine();

        File file = new File(nombreCatalogo+".cat");
        if (file.exists()) catalogo = Catalogo.leer(nombreCatalogo);
        else catalogo = new Catalogo(nombreCatalogo);
        System.out.println();

        String peticion;
        do {
            peticion = leerPeticion();
        } while (procesandoPeticion(peticion));
    }
    private String listaOpciones() {
        StringBuilder sb = new StringBuilder("Lista de opciones de "+catalogo.getNombre()+"\n");
        sb.append("\t1. añadir <marca> <modelo(opcional)> <color(solo si tiene modelo; opcional)> <talla> <precio(decimal con .)>:" +
                        "Añade una zapatilla al catálogo.\n")
                .append("\t3. lista: Muestra la lista de zapatillas.\n")
                .append("\t4. grabar: Guarda el catálogo.\n")
                .append("\t6. salir: Sale del programa.\n")
                .append("\tPor favor, introduzca cada dato sin espacios adicionales.\n");
        return sb.toString();
    }
    private String leerPeticion() {
        System.out.print(listaOpciones()+"Introduce tu petición: ");
        String entrada = teclado.nextLine();
        return entrada;
    }
    private boolean procesandoPeticion(String entrada) {
        String[] peticion = entrada.split("\\s+");
        if (peticion[0].equalsIgnoreCase("añadir")) return add(peticion);
        else if (peticion[0].equalsIgnoreCase("lista")) {
            System.out.println(catalogo+"\n");
            return true;
        }
        else if (peticion[0].equalsIgnoreCase("grabar")) {
            catalogo.grabar();
            return true;
        }
        else if (peticion[0].equalsIgnoreCase("salir")) {
            System.out.println("Saliendo del programa.");
            return false;
        }
        else {
            System.out.println("Petición errónea.");
            System.out.println();
            return true;
        }
    }
    private boolean add(String[] peticion) {
        try {
            /* if (peticion.length <= 4) { //Zapatilla básica
                double precio = Double.parseDouble(peticion[4]);
                catalogo.add(new Zapatilla(peticion[1], peticion[2], peticion[3], precio));
                System.out.println();
                return true;
            }
            else if () {} //Zapatilla con modelo
            else {} //Zapatilla con color */
            double precio = Double.parseDouble(peticion[4]);
            catalogo.add(new Zapatilla(peticion[1], peticion[2], peticion[3], precio));
            System.out.println();
            return true;
        } catch(ArrayIndexOutOfBoundsException e) {
                /* Dará este error cuando el usuario haya introducido menos o más datos de los necesarios,
                pues "peticion[]" será más o menos corta que lo que la creación del contacto requiere. */
            System.out.println("Por favor, introduce la información con los espacios indicados.");
            System.out.println();
            return true;
        }
    }


    //Métodos de utilidad
    public static boolean confirmacion(String pregunta) {
        boolean resultado = true; String siNo;
        do {
            System.out.println(pregunta+" (si/no): ");
            siNo = teclado.nextLine();

            if (siNo.equalsIgnoreCase("no") || siNo.equalsIgnoreCase("n"))
                resultado = false;
            else if(!(siNo.equalsIgnoreCase("si") || siNo.equalsIgnoreCase("s")))
                System.out.println("Respuesta incorrecta, inténtalo de nuevo.");

        } while(!(siNo.equalsIgnoreCase("si") || siNo.equalsIgnoreCase("s") ||
                siNo.equalsIgnoreCase("no") || siNo.equalsIgnoreCase("n")));
        return resultado;
    }
}