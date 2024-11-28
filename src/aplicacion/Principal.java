package aplicacion;
import dominio.*;
import dominio.productos.*;

public class Principal {
    public static void main(String[] args) {
        /* Antes de hacerla abstracta...
        Producto producto1 = new Producto();
        Producto producto2 = new Producto("Perro", 45);
        System.out.println("Producto 1: "+producto1.getNombre()+" "+producto1.getId()+
                "\nProducto 2: "+producto2.getNombre()+" "+producto2.getId());
        // OUTPUT:
        // Producto 1:  null
        // Producto 2: Perro null
         */
        System.out.println(Ansi.Blue.and(Ansi.Underline).colorize("BOOM!"));
    }
}