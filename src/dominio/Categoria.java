package dominio;

import dominio.productos.*;
import java.util.ArrayList;

public class Categoria {
    private ArrayList<Producto> ProductosModa = new ArrayList<>();
    private int modaCount = 0;


    //Constructores:
    


    //Getters y Setters:
    public int getModaCount() {return modaCount;}
    public  ArrayList<Producto> getProductosModa() {return ProductosModa;}


    //Métodos de Moda:
    public static boolean addModa(Producto producto) throws ProductoDuplicado {
        if (ProductosModa.contains(producto)) {
            throw new ProductoDuplicado(producto);
        }
        else {
            ProductosModa.add(producto);
            modaCount++;
            return true;
        }
    }
    //buscar(Producto)
    //remove(Producto)
}