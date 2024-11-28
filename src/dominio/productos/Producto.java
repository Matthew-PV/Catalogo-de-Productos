package dominio.productos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Producto {
    protected String id, nombre;
    protected double precio;
    //Servicios del Producto:
    private final DecimalFormat euros = new DecimalFormat("#,##0.00€", new DecimalFormatSymbols(Locale.forLanguageTag("es-ES")));
    protected final String precioEnEuros = euros.format(precio);


    //Constructores:
    public Producto() {nombre = "";}
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


    //Getters y Setters:
    public String getId() {return id;}
    public String getNombre() {return nombre;}
    public Producto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public double getPrecio() {return precio;}
    public Producto setPrecio(double precio) {
        this.precio = precio;
        return this;
    }


    //Métodos de Producto:
    public String toString() {
        return new StringBuilder().append(id).append(' ').append(nombre).toString().trim();
    }
    public boolean equals(Object otro) {
        if (otro != null && otro.getClass() == this.getClass()) {
            Producto producto = (Producto) otro;
            return id.equalsIgnoreCase(producto.id);
        }
        return false;
    }
}