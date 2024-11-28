package dominio.productos;

public class Zapatillas extends Producto{
    private String marca;
    private static int contadorZapatillas = 0;


    //Constructores:
    public Zapatillas(String nombre, String marca, double precio) {
        super(nombre, precio);
        this.marca = marca;
        contadorZapatillas++;
    }


    //Getters y Setters:
    public String getMarca() {return marca;}
    public Zapatillas setMarca(String marca) {
        this.marca = marca;
        return this;
    }


    //Métodos de Zapatilla:
    public String generarID(String abreviaturaCategoria) {
        return contadorZapatillas+"-zapatillas-"+abreviaturaCategoria;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(marca).append(' ').append(super.toString());
        return sb.toString().trim();
    }
}