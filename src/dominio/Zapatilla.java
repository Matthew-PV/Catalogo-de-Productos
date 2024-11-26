package dominio;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Zapatilla extends Producto implements Serializable {
    private String marca, modelo, color, talla;
    private double precio;
    private final DecimalFormat euros = new DecimalFormat("#.##");


    //Constructores
    public Zapatilla() {}
    public Zapatilla(String marca, String talla, double precio) {
        this.marca = marca;
        this.talla = talla;
        this.precio = precio;
    }
    public Zapatilla(String marca, String color, String talla, double precio) {
        this.marca = marca;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
    }
    public Zapatilla(String marca, String modelo, String color, String talla, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
    }


    //Getters y Setters
    public String getMarca() {return marca;}
    public Zapatilla setMarca(String marca) {
        this.marca = marca;
        return this;
    }
    public String getModelo() {return modelo;}
    public Zapatilla setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }
    public String getColor() {return color;}
    public Zapatilla setColor(String color) {
        this.color = color;
        return this;
    }
    public String getTalla() {return talla;}
    public Zapatilla setTalla(String talla) {
        this.talla = talla;
        return this;
    }
    public double getPrecio() {return precio;}
    public Zapatilla setPrecio(double precio) {
        this.precio = precio;
        return this;
    }


    //Métodos de Zapatilla
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMarca: ").append(marca);
        if (modelo != null) {
            sb.append("\nModelo: ").append(modelo);
        }
        if (color != null) {
            sb.append("\nColor: ").append(color);
        }
        sb.append("\nTalla: ").append(talla).append("\nPrecio: ").append(euros.format(precio)).append('\n');
        return sb.toString();
    }
    public boolean equals(Object object) { //Serán iguales si son el mismo modelo y color con la misma talla.
        if (object != null) {
            Zapatilla zapatilla = (Zapatilla) object;
            return zapatilla.marca.equalsIgnoreCase(talla) && zapatilla.modelo.equalsIgnoreCase(modelo)
                    && zapatilla.color.equalsIgnoreCase(color) && zapatilla.talla.equalsIgnoreCase(talla);
        }
        else {
            return false;
        }
    }
}