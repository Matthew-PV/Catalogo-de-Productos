package control;

import dominio.Ansi;
import dominio.Catalogo;
import dominio.productos.ProductoDuplicado;
import dominio.productos.Zapatillas;
import interfaz.Interfaz;
import jdk.jshell.execution.Util;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Control {
    private static final Scanner teclado = new Scanner(System.in);


    //Métodos del menú principal de Interfaz:
    public static void crearCatalogo(Interfaz interfaz, String nombre) {
        Catalogo catalogo = new Catalogo(nombre);
        if (catalogo.exists()) {
            if (confirmacion("El catálogo "+Ansi.Blue(catalogo.getNombre())+" ya existe. ¿Deseas seleccionarlo?")) {
                interfaz.setCatalogo(Catalogo.leer(nombre));
            }
        }
        interfaz.setCatalogo(new Catalogo(nombre));
    }
    public static void lista() {
        File directorioActual = new File(".");
        if (directorioActual.exists() && directorioActual.isDirectory()) {
            //listFiles() crea una lista de los ficheros, y puede recibir un filtro opcional.
            File[] catalogos = directorioActual.listFiles(file -> file.isFile() && file.getName().endsWith(Catalogo.getExtension()));

            if (catalogos != null && catalogos.length > 0) {
                int index = 1;
                for (File file : catalogos) {
                    Catalogo catalogo = Catalogo.leer(file);
                    System.out.println("\t"+index+" "+Ansi.Blue(catalogo.getNombre()));
                    index++;
                }
                System.out.println();
            }
            else System.out.println("No existen catálogos actualmente.");

        }
        else {
            System.out.println("El directorio actual ("+directorioActual.getName()+") no es un directorio válido.");
        }
    }
    public static void seleccionar(Interfaz interfaz, String nombre) {
        Catalogo catalogo = new Catalogo(nombre);
        seleccionarCatalogo(interfaz, catalogo);
    }


    //Métodos del menú del Catálogo:
    public static void add(Interfaz interfaz, String tipoProducto) {
        switch (tipoProducto) {
            case "zapatillas", "zapatilla":
                System.out.println("Categorías disponibles:"+interfaz.getCatalogo().listaCategorias());
                addZapatillas(interfaz);
                break;
            default:
                System.out.println(Ansi.Red(tipoProducto)+" no es un producto disponible en este momento.");
        }
    }


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


    //Métodos de Catálogo:
    private static boolean catalogoActivo(Interfaz interfaz) {return interfaz.getCatalogo() != null;}

    public static void guardarCambios(Interfaz interfaz) {
        if (catalogoActivo(interfaz) && //Si la interfaz tiene un catálogo activo en el momento.
                Control.confirmacion("¿Deseas guardar los cambios del catálogo "+Ansi.Blue(interfaz.getCatalogo().getNombre())+"?")) {
            interfaz.getCatalogo().guardar();
        }
    }

    private static boolean seleccionarCatalogo(Interfaz interfaz, Catalogo catalogo) {
        if (catalogo.exists()) {
            if (Control.confirmacion("¿Deseas seleccionar el catálogo "+Ansi.Blue(catalogo.getNombre())+"?"))
                interfaz.setCatalogo(Catalogo.leer(catalogo.getNombre()));
            return true;
        }
        else {
            if (confirmacion("El catálogo "+Ansi.Blue(catalogo.getNombre())+" no existe. ¿Deseas crearlo?"))
                crearCatalogo(interfaz, catalogo.getNombre());
            return false;
        }
    }
    private static boolean seleccionarCatalogo(Interfaz interfaz, String nombre) {
        Catalogo catalogo = new Catalogo(nombre);
        if (catalogo.exists()) {
            if (Control.confirmacion("¿Deseas seleccionar el catálogo "+Ansi.Blue(catalogo.getNombre())+"?"))
                interfaz.setCatalogo(Catalogo.leer(nombre));
            return true;
        }
        else {
            if (confirmacion("El catálogo "+Ansi.Blue(catalogo.getNombre())+" no existe. ¿Deseas crearlo?"))
                crearCatalogo(interfaz, nombre);
            return false;
        }
    }


    //Métodos de Productos:
    public static void addZapatillas(Interfaz interfaz) {
        System.out.println("Para añadir Zapatillas, introdúcelas con el siguiente formato:");
        System.out.print(Ansi.Blue("<categoria>")+Ansi.Cyan(" <marca> <nombre/modelo> <precio(decimal con punto)")+": ");
        String[] peticion = teclado.nextLine().split("\\s+");
        try {
            interfaz.getCatalogo().addProductoACategoria(new Zapatillas(peticion[2], peticion[1], Double.parseDouble(peticion[3])), peticion[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Ansi.Red(Ansi.Italic("Petición incorrecta."))+" Introduce la información con la cantidad de parámetros indicados.");
        } catch (ProductoDuplicado e) {
            System.out.println(Ansi.Red(e.getMessage()));
        } catch (InputMismatchException e) {
            System.out.println(Ansi.Red("Datos incorrectos.")+" Introduce la información con el formato indicado.");
        }
    }
}