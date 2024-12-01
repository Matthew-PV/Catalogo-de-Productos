package aplicacion;
import dominio.*;
import dominio.productos.*;
import interfaz.Interfaz;

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


        /*
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
        System.out.println(catalogo1+"\n");


        try {
            System.out.println(catalogo1.getProducto(NewBalance.getId(), "Moda")+"\n");
        } catch (ProductoNoEncontrado e) {
            System.out.println(e.getMessage());
        }


        for (Producto producto : catalogo1.getProductos()) {
            System.out.println(producto+" "+producto.getId());
        }
        System.out.println();

        OUTPUT:
530-N/A
1-zapatillas-MD
Test
	La categoría de Electrónica está vacía.

	MODA
	New Balance 530 120€
	Nike Air Force 99,46€
	Nike Air Force 99,46€

	La categoría de Música está vacía.

New Balance 530 120€

New Balance 530 120€ 1-zapatillas-MD
Nike Air Force 99,46€ 2-zapatillas-MD
Nike Air Force 99,46€ 3-zapatillas-MD
         */

        /*
        Zapatillas zapatillas1 = new Zapatillas("Air Force One", "Nike", 35.6);
        zapatillas1.modificarZapatilla(new String[]{"modelo", "Air Max"});
        zapatillas1.modificarZapatilla(new String[]{"marca", "Adidas"});
        zapatillas1.modificarZapatilla(new String[] {"precio", "44,657"});
        zapatillas1.modificarZapatilla(new String[] {"precio", "44.657"});

        System.out.println(zapatillas1);

        OUTPUT:
¿Estás seguro de que quieres cambiar el modelo de Air Force One a Air Max? (si/no): s
¿Estás seguro de que quieres cambiar la marca de Nike a Adidas? (si/no): s
¿Estás seguro de que quieres cambiar el precio de 35,6€ a 44,657? (si/no): s
El precio debe ser un número (decimales separados por un punto).
¿Estás seguro de que quieres cambiar el precio de 35,6€ a 44.657? (si/no): s
Adidas Air Max 44,66€
         */
        Interfaz interfaz = new Interfaz();
        interfaz.ejecutar();
    }
}