package dominio;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Producto {
    protected Categoria categoria;
    protected String nombre;
    protected double precio;
    private final DecimalFormat euros = new DecimalFormat("#,##0.00€", new DecimalFormatSymbols(Locale.forLanguageTag("es-ES")));
    protected final String precioEnEuros = euros.format(precio);


    //Constructores
    public Producto() {nombre = "";}
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


    //Getters y Setters

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
}