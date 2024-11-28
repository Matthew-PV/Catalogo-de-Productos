package control;

import java.util.Scanner;

public class Control {
    private final Scanner teclado = new Scanner(System.in);


    //Métodos de Interfaz:


    //Métodos de Control:
    public boolean confirmacion(String pregunta) {
        String respuesta;
        do {
            System.out.print(pregunta+" (si/no): ");
            respuesta = teclado.nextLine();
        } while(!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("s") ||
                respuesta.equalsIgnoreCase("no") || respuesta.equalsIgnoreCase("n")));
        return respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("s");
    }
}