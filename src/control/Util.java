package control;

import dominio.Ansi;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {
    private static final Scanner teclado = new Scanner(System.in);


    //Métodos de Interfaz:


    //Métodos de Control:
    /**
     * Solicita al usuario confirmar una acción mediante un mensaje.
     * Solo acepta como respuesta válida "si", "s", "no" o "n" (en cualquier combinación de mayúsculas y minúsculas).
     *
     * @param pregunta El mensaje que se mostrará al usuario solicitando confirmación.
     * @return {@code true} si el usuario responde "si" o "s", {@code false} si responde "no" o "n".
     */
    public static boolean confirmacion(String pregunta) {
        ArrayList<String> respuestasValidas = new ArrayList<>();
        respuestasValidas.add("si"); respuestasValidas.add("s");
        respuestasValidas.add("no"); respuestasValidas.add("n");

        String respuesta;
        do {
            System.out.print(pregunta + Ansi.Yellow.colorize(" (si/no)") + ": ");
            respuesta = teclado.nextLine().trim().toLowerCase();
            if (!respuestasValidas.contains(respuesta))
                System.out.println(Ansi.Red.colorize("Respuesta incorrecta."));
        } while(!respuestasValidas.contains(respuesta));

        return respuesta.equals("si") || respuesta.equals("s");
    }
}