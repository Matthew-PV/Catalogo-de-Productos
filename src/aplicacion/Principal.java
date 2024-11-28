package aplicacion;
import control.Control;
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
        Catalogo catalogo1 = new Catalogo("Test");
        Zapatillas NewBalance = new Zapatillas("530","New Balance", 120);
        System.out.println(NewBalance.getId());
        try {
            catalogo1.addProductoACategoria(NewBalance, "Moda");
            System.out.println(NewBalance.getId());
            catalogo1.addProductoACategoria(new Zapatillas("Air Force", "Nike", 99.46), "Moda");
            Zapatillas AirForce = new Zapatillas("Air Force", "Nike", 99.46);
            catalogo1.addProductoACategoria(AirForce, "Moda");
        } catch (ProductoDuplicado e) {
            System.out.println(e.getMessage());
        }
        System.out.println(catalogo1);
    }
}