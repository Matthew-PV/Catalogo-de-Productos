package dominio;

import java.util.List;

public class ManejadorCategorias {
    private final List<Categoria> categoriasPredefinidas;
    private static final ManejadorCategorias instancia = new ManejadorCategorias();


    //Constructor de la instancia única donde puedo agregar las clases que serán fijas para el programa:
    public ManejadorCategorias() {
        categoriasPredefinidas = List.of(
                new Categoria("moda", "MD"),
                new Categoria("musica", "MS"),
                new Categoria("electronica","E")
        );
    }


    //Métodos de acceso a las categorías:
    public static ManejadorCategorias getInstancia() {return instancia;}
    public List<Categoria> getCategoriasPredefinidas() {return categoriasPredefinidas;}
}