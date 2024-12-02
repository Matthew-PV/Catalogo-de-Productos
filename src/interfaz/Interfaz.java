package interfaz;

import control.Control;
import dominio.Ansi;
import dominio.Catalogo;
import jdk.jshell.execution.Util;

import java.util.List;
import java.util.Scanner;

public class Interfaz {
    private static final Scanner teclado = new Scanner(System.in);
    private Catalogo catalogo;
    private final List<String> opcionesPrincipal;


    //Constructor de la Interfaz:
    public Interfaz() {
        opcionesPrincipal = List.of(
                (Ansi.Magenta("crear")+" "+Ansi.Italic(Ansi.Blue("<nombre del catálogo>"))+
                        ": Crea un nuevo catálogo."),
                (Ansi.Magenta("lista")+": Muestra la lista de catálogos disponibles."),
                (Ansi.Magenta("seleccionar")+" "+Ansi.Italic(Ansi.Blue("<catalogo>"))+
                        ": Selecciona un catálogo por su nombre."),
                (Ansi.Magenta("menú catálogo")+": Accede al menú del catálogo."),
                (Ansi.Bold(Ansi.Red("salir"))+": Cierra el programa.")
        );
    }


    //Getters y Setters de la Interfaz:
    public Catalogo getCatalogo() {return catalogo;}
    public Interfaz setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
        return this;
    }


    //Menús de la Interfaz:
    public String menuPrincipal() {
        int indice = 1;
        StringBuilder sb = new StringBuilder("MENU PRINCIPAL");
        for (String opcion : opcionesPrincipal) {
            sb.append("\n\t").append(indice).append(' ').append(opcion);
            indice++;
        }
        sb.append('\n');
        return sb.toString();
    }


    //Métodos de Interfaz:
    public void ejecutar() {
        System.out.println(Ansi.Bold("====="+Ansi.Underline("¡Bienvenido al gestor de catálogos!")+Ansi.Bold("=====")));

        String peticion;
        do {
            System.out.println(menuPrincipal());
            peticion = leerPeticion();
        } while (peticionPrincipal(peticion));
    }


    //Proceso de peticiones:
    public String leerPeticion() {
        System.out.print("Introduce tu petición: ");
        return teclado.nextLine();
    }
    public boolean peticionPrincipal(String entrada) {
        String[] peticion = entrada.split("\\s+");
        String instruccion = peticion[0];

        try {
            switch (instruccion) {
                case "crear", "c":
                    Control.guardarCambios(this);
                    Control.crearCatalogo(this, peticion[1]);
                    break;
                case "lista", "ls":
                    Control.lista();
                    break;
                case "seleccionar", "sl":
                    Control.guardarCambios(this);
                    Control.seleccionar(this, peticion[1]);
                    break;
                case "salir", "x":
                    Control.guardarCambios(this);
                    System.out.println(Ansi.Bold("=====" + Ansi.Underline("¡Que tengas un buen día!") + Ansi.Bold("=====")));
                    return false;
                default:
                    System.out.println(Ansi.Red(instruccion) + " es una instrucción incorrecta.");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Ansi.Red(Ansi.Italic("Petición incorrecta."))+" Introduce la información con la cantidad de parámetros indicados.");
        }
        return true;
    }
}