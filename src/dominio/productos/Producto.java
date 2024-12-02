package dominio.productos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Producto implements Serializable {
    protected String id;
    /*
    El id resulta problemático, pues depende de la categoría a la que pertenece el producto.
    Sin embargo, se necesita saber el id del producto antes de añadirlo a cualquier categoría,
    para comprobar si el producto ya existe.
    SOLUCIÓN: Crear una id temporal que solo dependa del nombre del producto.
     */
    protected String nombre;
    protected double precio;
    //Servicios del Producto:
    private final DecimalFormat euros = new DecimalFormat("0.##€", new DecimalFormatSymbols(Locale.forLanguageTag("es-ES")));
    protected String precioEnEuros;


    //Constructores:
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        id = generarID();
        precioEnEuros = euros.format(precio);
    }


    //Getters y Setters de Producto:
    public String getId() {return id;}
    public Producto setID(String id) {
        this.id = id;
        return this;
    }
    public String getNombre() {return nombre;}
    public Producto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public double getPrecio() {return precio;}
    public Producto setPrecio(double precio) {
        this.precio = precio;
        precioEnEuros = euros.format(precio);
        return this;
    }


    //Métodos de Producto:
    public String generarID() {return nombre+"-N/A";}
    public String generarID(String abreviaturaCategoria) {return nombre+"-"+abreviaturaCategoria;}

    public abstract boolean modificar(String[] modificacion);

    public String toString() {return nombre+" "+precioEnEuros;}
    public boolean equals(Object otro) {
        if (otro != null && otro.getClass() == this.getClass() && id != null) { //Si el objeto es de la misma clase...
            Producto producto = (Producto) otro;
            return id.equalsIgnoreCase(producto.id);
        }
        else if (id == null) return false;
        return false;
    }
}