package dominio;

import control.Control;
import dominio.productos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Catalogo implements Serializable {
    private String nombre, nombreArchivo;
    private static final String extension = ".ca";
    private Map<String, Categoria> categorias;
    private final ArrayList<Producto> productos = new ArrayList<>();
    //Para que cada catálogo tenga categorías preseleccionadas, fijas e independientes entre catálogos,
    //utilizamos un contenedor HashMap para cada uno.


    //Constructores:
    public Catalogo() {}
    public Catalogo (String nombre) {
        this.nombre = nombre;
        nombreArchivo = this.nombre+extension;
        categorias = new HashMap<>();
        for (Categoria categoria : ManejadorCategorias.getInstancia().getCategoriasPredefinidas()) {
            categorias.put(categoria.getNombre(), new Categoria(categoria.getNombre(), categoria.getAbreviatura()));
        } //Adición de las categorías
    }


    //Getters y Setters de Catálogo:
    public String getNombre() {return nombre;}
    public Catalogo setNombre(String nombre) {
        this.nombre = nombre;
        nombreArchivo = this.nombre+extension;
        return this;
    }
    public static String getExtension() {return extension;}
    public String getNombreArchivo() {return nombreArchivo;}
    public ArrayList<Producto> getProductos() {return productos;}


    //Métodos de Categoria:
    public Categoria getCategoria(String nombreCategoria) {return categorias.get(nombreCategoria);}
    public Catalogo addProductoACategoria(Producto producto, String nombreCategoria) throws ProductoDuplicado {
        getCategoria(nombreCategoria).addProducto(producto);
        productos.add(producto);
        return this;
    }
    public String listaCategorias() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Categoria categoria : categorias.values()) {
            sb.append("\n\t").append(index).append(' ').append(categoria.getNombre());
            index++;
        }
        return sb.toString();
    }


    //Métodos de Producto:
    public boolean modificarProducto(Producto producto, String nombreCategoria, String[] modificacion)
            throws ProductoNoEncontrado, ArrayIndexOutOfBoundsException {
        return getCategoria(nombreCategoria).modificarProducto(producto, modificacion);
    }

    public Producto getProducto(Producto producto, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).getProducto(producto);
    }
    public Producto getProducto(String id, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).getProducto(id);
    }

    public boolean eliminarProducto(Producto producto, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).eliminarProducto(producto);
    }
    public boolean eliminarProducto(String id, String nombreCategoria) throws ProductoNoEncontrado {
        return getCategoria(nombreCategoria).eliminarProducto(id);
    }


    //Métodos de Catalogo:
    public static Catalogo leer(String nombre) {
        try {
            ObjectInputStream fi = new ObjectInputStream(new FileInputStream(nombre+extension));
            Catalogo catalogo = (Catalogo) fi.readObject();
            fi.close();
            return catalogo;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Ansi.Red("Error de lectura."));
            return new Catalogo();
        }
    }
    public static Catalogo leer(File file) {
        try {
            ObjectInputStream fi = new ObjectInputStream(new FileInputStream(file));
            Catalogo catalogo = (Catalogo) fi.readObject();
            fi.close();
            return catalogo;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Ansi.Red("Error de lectura"));
            return new Catalogo();
        }
    }

    public boolean guardar() {
        try {
            ObjectOutputStream fo = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
            if (exists()) {
                if (Control.confirmacion("El catálogo " + Ansi.Blue(nombre) + " ya existe. ¿Deseas sobreescribirlo?")) {
                    new File(nombreArchivo).delete();
                    fo.writeObject(this);
                    fo.close();
                    return true;
                } else {
                    return false;
                }
            }
            else return true;
        } catch (IOException e) {
            System.out.println(Ansi.Red("Error de guardado."));
            return false;
        }
    }

    public boolean borrar() {
        if (Control.confirmacion("¿Estás seguro de que quieres borrar el catálogo "+Ansi.Blue(nombre)+"?")) {
            if (exists()) return new File(nombreArchivo).delete();
            else System.out.println("El archivo del catálogo "+Ansi.Blue(nombre)+" no existe.");
        }
        return false;
    }
    public boolean exists() {
        File file = new File(nombreArchivo);
        boolean res = file.exists();
        file.delete();
        return res;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(Ansi.Bold(Ansi.Underline(Ansi.Blue(nombre))));
        for (Categoria categoria : categorias.values()) {
            sb.append("\n\t").append(categoria);
        }
        sb.append('\n');
        return sb.toString();
    }
}