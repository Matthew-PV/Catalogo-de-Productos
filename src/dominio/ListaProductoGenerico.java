package dominio;

import java.util.ArrayList;

public class ListaProductoGenerico<Producto> {
    private ArrayList<Producto> lista = new ArrayList<>();
    public void add(Producto producto) {
        lista.add(producto);
    }
    public Producto getProducto(int index) {
        return lista.get(index);
    }
    public int size() {return lista.size();}
}