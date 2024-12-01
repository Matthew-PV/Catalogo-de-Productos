package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Uso:
 * <li>String texto = Ansi.Red.and(Ansi.BgYellow).format("Hola %s", nombre)</li>
 * <li>String texto = Ansi.Blink.colorize("BOOM!")</li>
 *
 * También se pueden usar las constantes en el constructor, sin concatenar:
 * <li>String texto = new Ansi(Ansi.ITALIC, Ansi.GREEN).format("Verde dinero")</li>
 * O simplemente usar las constantes en texto:
 * <li>String texto = Ansi.BLUE + "científico"</li>
 *
 * NOTA: Se pueden concatenar varios colores, pero solo aparecerá el último.
 *
 * @author <a href="https://gist.github.com/dainkaplan">...</a>
 *
 */

public final class Ansi {
    /*
    Código basado en:
    https://gist.github.com/dainkaplan/4651352
    */
    final private String[] codigos;
    final private String stringCodigos;


    //Constantes de formateo ANSI
    public static final String	RESET				= "\u001B[0m";

    public static final String	HIGH_INTENSITY		= "\u001B[1m";
    public static final String	LOW_INTENSITY		= "\u001B[2m";

    public static final String	ITALIC				= "\u001B[3m";
    public static final String	UNDERLINE			= "\u001B[4m";
    public static final String	BLINK				= "\u001B[5m";
    public static final String	RAPID_BLINK			= "\u001B[6m";
    public static final String	REVERSE_VIDEO		= "\u001B[7m";
    public static final String	INVISIBLE_TEXT		= "\u001B[8m";

    public static final String	BLACK				= "\u001B[30m";
    public static final String	RED					= "\u001B[31m";
    public static final String	GREEN				= "\u001B[32m";
    public static final String	YELLOW				= "\u001B[33m";
    public static final String	BLUE				= "\u001B[34m";
    public static final String	MAGENTA				= "\u001B[35m";
    public static final String	CYAN				= "\u001B[36m";
    public static final String	WHITE				= "\u001B[37m";

    public static final String	BACKGROUND_BLACK	= "\u001B[40m";
    public static final String	BACKGROUND_RED		= "\u001B[41m";
    public static final String	BACKGROUND_GREEN	= "\u001B[42m";
    public static final String	BACKGROUND_YELLOW	= "\u001B[43m";
    public static final String	BACKGROUND_BLUE		= "\u001B[44m";
    public static final String	BACKGROUND_MAGENTA	= "\u001B[45m";
    public static final String	BACKGROUND_CYAN		= "\u001B[46m";
    public static final String	BACKGROUND_WHITE	= "\u001B[47m";


    //Constructores:
    public Ansi(String... codigos) { //Crea un objeto que admite una cantidad variable de parámetros
        this.codigos = codigos;
        StringBuilder stringCodigos = new StringBuilder();
        for (String codigo : codigos) {
            stringCodigos.append(codigo);
        }
        this.stringCodigos = stringCodigos.toString();
    }
    public Ansi and(Ansi otro) { //Concatena la lista de códigos de ambos objetos
        List<String> combinacion = new ArrayList<String>();
        Collections.addAll(combinacion, codigos);
        Collections.addAll(combinacion, otro.codigos);
        return new Ansi(combinacion.toArray(new String[] {}));
    }


    //Objetos constantes:
    public static final Ansi HighIntensity = new Ansi(HIGH_INTENSITY);
    public static final Ansi Bold = HighIntensity;

    /**
     * Escribe texto en negrita.
     * @param text String a formatear.
     * @return String escrito en negrita.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Bold(String text) {return Bold.colorize(text);}
    public static final Ansi LowIntensity = new Ansi(LOW_INTENSITY);
    public static final Ansi Normal = LowIntensity;
    public static final Ansi Italic = new Ansi(ITALIC);

    /**
     * Escribe texto en cursiva.
     * @param text String a formatear.
     * @return String escrito en cursiva.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Italic(String text) {return Italic.colorize(text);}
    public static final Ansi Blink = new Ansi(BLINK);
    public static final Ansi RapidBlink = new Ansi(RAPID_BLINK);
    public static final Ansi Underline = new Ansi(UNDERLINE);

    /**
     * Escribe texto subrayado.
     * @param text String a formatear.
     * @return String subrayado.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Underline(String text) {return Underline.colorize(text);}

    public static final Ansi Black = new Ansi(BLACK);

    /**
     * Escribe texto de color negro.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Black(String text) {return Black.colorize(text);}
    public static final Ansi Red = new Ansi(RED);

    /**
     * Escribe texto de color rojo.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Red(String text) {return Red.colorize(text);}
    public static final Ansi Green = new Ansi(GREEN);

    /**
     * Escribe texto de color verde.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Green(String text) {return Green.colorize(text);}
    public static final Ansi Yellow = new Ansi(YELLOW);

    /**
     * Escribe texto de color amarillo.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Yellow(String text) {return Yellow.colorize(text);}
    public static final Ansi Blue = new Ansi(BLUE);

    /**
     * Escribe texto de color azul.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Blue(String text) {return Blue.colorize(text);}
    public static final Ansi Magenta = new Ansi(MAGENTA);

    /**
     * Escribe texto de color magenta.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Magenta(String text) {return Magenta.colorize(text);}
    public static final Ansi Cyan = new Ansi(CYAN);

    /**
     * Escribe texto de color cian.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String Cyan(String text) {return Cyan.colorize(text);}
    public static final Ansi White = new Ansi(WHITE);

    /**
     * Escribe texto de color blanco.
     * @param text String a formatear.
     * @return String coloreada.
     * @author Matthew Puente-Villegas Michavila.
     */
    public static String White(String text) {return White.colorize(text);}


    public static final Ansi BgBlack = new Ansi(BACKGROUND_BLACK);
    public static final Ansi BgRed = new Ansi(BACKGROUND_RED);
    public static final Ansi BgGreen = new Ansi(BACKGROUND_GREEN);
    public static final Ansi BgYellow = new Ansi(BACKGROUND_YELLOW);
    public static final Ansi BgBlue = new Ansi(BACKGROUND_BLUE);
    public static final Ansi BgMagenta = new Ansi(BACKGROUND_MAGENTA);
    public static final Ansi BgCyan = new Ansi(BACKGROUND_CYAN);
    public static final Ansi BgWhite = new Ansi(BACKGROUND_WHITE);


    //Métodos para formatear texto:
    public String colorize(String texto) {return stringCodigos + texto + RESET;}
    //Método redefinido de "String.format()", es colorear pero permitiendo formateo de Strings ("Hola %s", nombre):
    public String format(String texto, Object... args) {return colorize(String.format(texto, args));}
}