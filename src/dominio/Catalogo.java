package dominio;
import interfaz.Interfaz;
import java.io.*;
import java.util.ArrayList;

public class Catalogo implements Serializable {
    private String nombre;

    private ArrayList<Zapatilla> zapatillas;


    //Constructores
    public Catalogo() {
        nombre = "";
        zapatillas = new ArrayList<>();
    }

    public Catalogo(String nombre) {
        this.nombre = nombre;
        zapatillas = new ArrayList<>();
    }


    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public Catalogo setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }


    //Trabajo con Zapatillas
    public ArrayList<Zapatilla> getZapatillas() {
        return zapatillas;
    }

    public Catalogo setZapatillas(ArrayList<Zapatilla> zapatillas) {
        this.zapatillas = zapatillas;
        return this;
    }

    public Zapatilla getZapatilla(int index) {
        return zapatillas.get(index);
    }

    public Catalogo add(Zapatilla zapatilla) {
        zapatillas.add(zapatilla);
        return this;
    }

    public int size() {
        return zapatillas.size();
    }

    public Zapatilla buscar(Zapatilla zapatilla) {
        int index = zapatillas.indexOf(zapatilla);
        if (index == -1) return null;
        else return zapatillas.get(index);
    }


    //Métodos de Catálogo
    public static Catalogo leer(String nombre) {
        try {
            ObjectInputStream fi = new ObjectInputStream(new FileInputStream(nombre + ".cat"));
            Catalogo catalogo = (Catalogo) fi.readObject();
            fi.close();
            return catalogo;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error de lectura.");
            return new Catalogo();
        }
    }

    public void grabar() {
        try {
            File file = new File(nombre + ".cat");
            ObjectOutputStream fo = new ObjectOutputStream(new FileOutputStream(nombre + ".cat"));
            if (file.exists()) {
                if (Interfaz.confirmacion("Ya existe un catálogo llamado " +
                        nombre + ". ¿Deseas sobreescribirlo?")) {
                    file.delete();
                    fo.writeObject(this);
                    fo.close();
                }
            } else {
                fo.writeObject(this);
                fo.close();
            }
        } catch (IOException e) {
            System.out.println("Error de escritura.");
        }
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (this.getClass() != object.getClass()) return false;
        Catalogo catalogo = (Catalogo) object;
        return catalogo.nombre.equalsIgnoreCase(nombre);
    }

    public String toString() {
        if (!zapatillas.isEmpty()) {
            try {
                StringBuilder sb = new StringBuilder("Catálogo ").append(nombre);
                int index = 1;
                for (Zapatilla zapatilla : zapatillas) {
                    sb.append("\n\t").append(index).append(". ").append(zapatilla);
                    index++;
                }
                return sb.toString();
            } catch (NullPointerException e) {
                return "No hay productos en el catálogo..";
            }
        } else return "No hay productos en el catálogo.";
    }
}