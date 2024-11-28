package dominio;

import java.util.List;

public class ManejadorCategorias {
    private final List<Categoria> categoriasPredefinidas;
    private static final ManejadorCategorias instancia = new ManejadorCategorias();


    //Constructor de la instancia única:
    public ManejadorCategorias() {
        categoriasPredefinidas = List.of(
                new Categoria("Moda", "MD"),
                new Categoria("Música", "MS"),
                new Categoria("Electrónica","E")
        );
    }


    //Métodos de acceso a las categorías:
    public static ManejadorCategorias getInstancia() {return instancia;}
    public List<Categoria> getCategoriasPredefinidas() {return categoriasPredefinidas;}
}