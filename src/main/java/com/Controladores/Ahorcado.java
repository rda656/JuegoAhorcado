package com.Controladores;

/**
 * Clase que gestiona la lógica de juego.
 * @author Antonio Ramos
 * @author 1.0
 */
public class Ahorcado {
    /**
     * Constante con el número máximo de errores que se permiten.
     */
    private static final int NUM_MAX_ERRORES = 6;
    
    /**
     * Constante con el número máximo de letras que tiene la palabra.
     */
    private static final int NUM_MAX_LETRAS = 10;
    
    /**
     * Array bidimensional que guarda la palabra y su definición.
     */
    private static String[][] palabras = {        
        {"AJARDINABA", "Convertir en jardín un terreno."},
        {"BOBINADORA", "Máquina destinada a hilar y a bobinar."},
        {"CUADRATURA", "Situación relativa de dos cuerpos celestes, que en longitud o en ascensión recta distan entre sí respectivamente uno o tres cuartos de círculo."},
        {"DALTONISMO", "Defecto de la vista que consiste en no percibir determinados colores o en confundir algunos de los que se perciben."},
        {"EQUILIBRAR", "Compensar las masas de un mecanismo con el fin de evitar vibraciones perjudiciales en su funcionamiento."},
        {"FIABILIDAD", "Probabilidad de buen funcionamiento de algo."},
        {"GIGANTILLO", "Figura de enano de gran cabeza."},
        {"HABICHUELA", "Diminutivo de haba. Judía."},
        {"ICONOLOGIA", "Estudio de las imágenes y de su valor simbólico."},
        {"JUBILACION", "Pensión que recibe quien se ha jubilado."}
    };
    
    /**
     * Array de caracteres que almacena las letras correctas que introduce el usuario.
     */
    private static char[] respuesta = new char[10];    
    
    /**
     * Palabra que tiene en el array la palabra seleccionada para el juego.
     */
    private static int posicionPalabraBuscada;
       
    /**
     * Fallos cometidos por el jugador.
     */
    private static int fallos;
    
    /**
     * Cantidad de letras que ha encontrado el jugador.
     */
    private static int letrasEncontradas;
    
    
    /**
     * Método que elige una palabra para buscar y resetea la respuesta
     */
    public static void iniciarJuego(){
        fallos = 0;
        letrasEncontradas = 0;
        
        for (int i = 0; i < respuesta.length; i++)
            respuesta[i] = '_';
        
        posicionPalabraBuscada = (int)(Math.random()*palabras.length);  
    }
    
    /**
     * Método en el que se desarrolla el juego hasta que el usuario gana o pierde.
     */
    public static int jugar(char letra){
        int numLetras;
                   
        numLetras = verificarLetra(letra);

        if(numLetras == 0)
            fallos++;
        else
            letrasEncontradas += numLetras;
        
        if(fallos == NUM_MAX_ERRORES)
            return -1;
        else if(letrasEncontradas == NUM_MAX_LETRAS)
            return 1;
        else
            return 0;
    }
    
    /**
     * Comprueba si la letra pasada por parámetro se encuentra en la palabra buscada y va añadiendolas al
     * array de la respuesta. Si ya se ha dicho la letra lo tomará como un fallo.
     * @param letra letra a buscar
     * @return número de coincidencias con la letra buscada
     */
    private static int verificarLetra(char letra){
        int contadorDeLetras = 0;        
        letra = Character.toUpperCase(letra);
        
        for(int i = 0; i < palabras[posicionPalabraBuscada][0].length(); i++){
            if(palabras[posicionPalabraBuscada][0].charAt(i) == letra){
                if(respuesta[i] == '_'){
                    respuesta[i] = letra;
                    contadorDeLetras++;
                }
                else
                    return 0;
            }
        }
        return contadorDeLetras;
    }

    /**
     * Devuelve el array de caracteres con las letras encontradas hasta el momento.
     * @return array con las letras encontradas.
     */
    public static char[] getRespuesta() {
        return respuesta;
    }

    /**
     * Devuelve la definición de la palabra seleccionada.
     * @return definición.
     */
    public static String getDefinicionPalabraBuscada() {
        return palabras[posicionPalabraBuscada][1];
    }

    /**
     * Devuelve el número de fallos cometidos.
     * @return entero con los fallos que lleva el jugador hasta el momento.
     */
    public static int getFallos() {
        return fallos;
    } 
    
}
