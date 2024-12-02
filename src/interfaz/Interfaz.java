package interfaz;

import control.Control;
import dominio.Ansi;
import dominio.Catalogo;

import java.util.List;
import java.util.Scanner;

public class Interfaz {
    private static final Scanner teclado = new Scanner(System.in);
    private Catalogo catalogo;
    private final List<String> opcionesPrincipal;
    private final List<String> opcionesCatalogo;


    //Constructor de la Interfaz:
    public Interfaz() {
        opcionesPrincipal = List.of(
                (Ansi.Magenta("crear")+" "+Ansi.Italic(Ansi.Yellow("<nombre del catálogo>"))+
                        ": Crea un nuevo catálogo."),
                (Ansi.Magenta("lista")+": Muestra la lista de catálogos disponibles."),
                (Ansi.Magenta("seleccionar")+" "+Ansi.Italic(Ansi.Yellow("<catalogo>"))+
                        ": Selecciona un catálogo por su nombre."),
                (Ansi.Green("catálogo")+": Accede al menú del catálogo."),
                (Ansi.Bold(Ansi.Red("salir"))+": Cierra el programa.")
        );
        opcionesCatalogo = List.of(
                (Ansi.Green("mostrar")+": Muestra las categorías y productos del catálogo."),
                (Ansi.Green("añadir")+" "+Ansi.Cyan("<tipoProducto>")+
                        ": Añade un producto de un tipo predefinido a una de las categorías del catálogo.\n\t\t" +
                        "Tipos de productos disponibles actualmente: Zapatillas."),
                (Ansi.Magenta("atrás")+(": Vuelve al menú principal."))
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
        StringBuilder sb = new StringBuilder("MENÚ PRINCIPAL");
        for (String opcion : opcionesPrincipal) {
            sb.append("\n\t").append(indice).append(' ').append(opcion);
            indice++;
        }
        sb.append('\n');
        return sb.toString();
    }
    public String menuCatalogo() {
        int indice = 1;
        StringBuilder sb = new StringBuilder("MENÚ DEL CATÁLOGO "+Ansi.Bold(Ansi.Underline(Ansi.Blue(catalogo.getNombre()))));
        for (String opcion : opcionesCatalogo) {
            sb.append("\n\t").append(indice).append(' ').append(opcion);
            indice++;
        }
        sb.append('\n');
        return sb.toString();
    }


    //Métodos de Interfaz:
    public void ejecutar() {
        System.out.println(Ansi.Bold("====="+Ansi.Underline("¡Bienvenido al gestor de catálogos!")+Ansi.Bold("=====")));

        String peticionPrincipal;
        do {
            System.out.println(menuPrincipal());
            peticionPrincipal = leerPeticion();
        } while (procesandoPrincipal(peticionPrincipal));
    }


    //Proceso de peticiones:
    public String leerPeticion() {
        System.out.print("Introduce tu petición: ");
        return teclado.nextLine();
    }
    public boolean procesandoPrincipal(String entrada) {
        String[] peticion = entrada.split("\\s+");
        String instruccion = peticion[0].toLowerCase();

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
                case "catálogo", "catalogo":
                    try {
                        String peticionCatalogo;
                        do {
                            System.out.println(menuCatalogo());
                            peticionCatalogo = leerPeticion();
                        } while (procesandoCatalogo(peticionCatalogo));
                    } catch (NullPointerException e) {
                        System.out.println(Ansi.Red("Selecciona un catálogo primero."));
                    }
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

    public boolean procesandoCatalogo(String entrada) {
        String[] peticion = entrada.split("\\s+");
        String instruccion = peticion[0].toLowerCase();

        try {
            switch (instruccion) {
                case "mostrar":
                    System.out.println(catalogo);
                    break;
                case "añadir":
                    Control.add(this, peticion[1].toLowerCase());
                    Control.guardarCambios(this);
                    break;
                case "atras", "atrás":
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