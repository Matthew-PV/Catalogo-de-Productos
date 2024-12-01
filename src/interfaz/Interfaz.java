package interfaz;

import control.Util;
import dominio.Ansi;
import dominio.Catalogo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interfaz {
    private static final Scanner teclado = new Scanner(System.in);
    private Catalogo catalogo;
    private List<String> opcionesPrincipal;


    //Constructor de la Interfaz:
    public Interfaz() {
        opcionesPrincipal = List.of(
                (Ansi.Magenta("crear")+" "+Ansi.Italic(Ansi.Blue("<nombre del catálogo>"))+
                        ": Crea un nuevo catálogo."),
                (Ansi.Magenta("lista")+": Muestra la lista de catálogos disponibles."),
                (Ansi.Magenta("seleccionar")+" "+Ansi.Italic(Ansi.Blue("<catalogo>"))+
                        ": Selecciona un catálogo por su nombre."),
                (Ansi.Bold(Ansi.Red("salir"))+": Cierra el programa.")
        );
    }


    //Menús de la Interfaz:
    public String menuPrincipal() {
        int indice = 1;
        StringBuilder sb = new StringBuilder("MENU PRINCIPAL");
        for (String opcion : opcionesPrincipal) {
            sb.append("\n\t").append(indice).append(' ').append(opcion);
        }
        sb.append('\n');
        return sb.toString();
    }


    //Métodos de Interfaz:
    public void ejecutar() {
        System.out.println(Ansi.Bold("====="+Ansi.Underline("¡Bienvenido al gestor de catálogos!")+"====="));
        System.out.println(menuPrincipal());
    }


    //Proceso de peticiones:
    public String leerPeticion() {
        System.out.print("Introduce tu petición: ");
        return teclado.nextLine();
    }
    public boolean peticionPrincipal(String entrada) {
        String[] peticion = entrada.split("\\s+");
        String instruccion = peticion[0];
        switch (instruccion) {
            case
        }
    }
}