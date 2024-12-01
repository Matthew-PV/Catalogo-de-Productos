package dominio.productos;

import control.Util;
import dominio.Ansi;
import java.io.Serializable;

public class Zapatillas extends Producto implements Serializable {
    private String marca; //El nombre equivale al modelo de zapatilla.
    private static int contadorZapatillas = 0;


    //Constructores:
    public Zapatillas(String nombre, String marca, double precio) {
        super(nombre, precio);
        this.marca = marca;
        id = generarID();
        contadorZapatillas++;
    }


    //Getters y Setters de Zapatillas:
    public String getMarca() {return marca;}
    public Zapatillas setMarca(String marca) {
        this.marca = marca;
        return this;
    }
    public static int getContadorZapatillas() {return contadorZapatillas;}


    //Métodos de Zapatilla:
    public boolean modificarZapatilla(String[] modificacion) throws ArrayIndexOutOfBoundsException {
        boolean res = true;
        String atributo = modificacion[0].toLowerCase();
        String valor = modificacion[1];

        switch (atributo) { //Todos los posibles atributos a modificar...
            case "nombre", "modelo":
                if (Util.confirmacion("¿Estás seguro de que quieres cambiar el modelo de "+
                        Ansi.Italic(Ansi.Blue(nombre))+" a "+Ansi.Italic(Ansi.Yellow(valor))+"?")) {
                    nombre = valor;
            }
                break;
            case "marca":
                if (Util.confirmacion("¿Estás seguro de que quieres cambiar la marca de "+
                        Ansi.Italic(Ansi.Blue(marca))+" a "+Ansi.Italic(Ansi.Yellow(valor))+"?")) {
                    marca = valor;
                }
                break;
            case "precio":
                if (Util.confirmacion("¿Estás seguro de que quieres cambiar el precio de "+
                        Ansi.Italic(Ansi.Blue(precioEnEuros))+" a "+Ansi.Italic(Ansi.Yellow(valor))+"?")) {
                    try {
                        setPrecio(Double.parseDouble(valor));
                    } catch (NumberFormatException e) {
                        System.out.println("El precio debe ser un número (decimales separados por un punto).");
                    }
                }
                break;
            default:
                System.out.println(Ansi.Red(atributo)+" no es una característica de las zapatillas.");
                res = false;
                break;
        }

        return res;
    }

    public String generarID(String abreviaturaCategoria) {
        return contadorZapatillas+"-zapatillas-"+abreviaturaCategoria;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(marca).append(' ').append(super.toString());
        return sb.toString();
    }
    public boolean equalsZapatilla(Zapatillas zapatillas) {
        if (!equals(zapatillas)) return false;
        else return marca.equals(zapatillas.marca) && nombre.equals(zapatillas.nombre);
    }
    //equals() por defecto implementado en la clase padre.
}