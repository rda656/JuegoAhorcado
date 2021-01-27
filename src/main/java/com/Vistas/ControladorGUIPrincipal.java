/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Vistas;

import com.Controladores.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Clase que se encarga de controlar la interfaz gráfica.
 * @author Antonio Ramos
 * @version 1.0
 */
public class ControladorGUIPrincipal implements Initializable {
    /**
     * Botón que permite iniciar un nuevo juego.
     */
    @FXML
    private Button btn_NuevoJuego;    
    
    /**
     * Botón que permite comprobar una letra.
     */
    @FXML
    private Button btn_ComprobarLetra; 
    
    /**
     * Campo de texto donde introducir la letra.
     */
    @FXML
    private TextField tf_Letra;
    
    /**
     * Círculo que simula la cabeza del muñeco.
     */
    @FXML
    private Circle c_Cabeza;

    /**
     * Resto de partes del cuerpo del muñeco.
     */
    @FXML
    private Line l_Cuerpo, l_BrazoDrcho, l_BrazoIzdo, l_PiernaDrcha, l_PiernaIzda;
    
    /**
     * Etiquetas que muestran cada una de las letras de la palabra.
     */
    @FXML
    private Label lbl_Palabra_01, lbl_Palabra_02, lbl_Palabra_03, lbl_Palabra_04, lbl_Palabra_05,
             lbl_Palabra_06, lbl_Palabra_07, lbl_Palabra_08, lbl_Palabra_09, lbl_Palabra_10;
    
    /**
     * Etiqueta que muestra la definición de la palabra que se busca.
     */
    @FXML
    private Label lbl_Definicion;
    
    /**
     * Array donde se almacenarán los elementos que componen el cuerpo del muñeco
     */
    private Node[] cuerpo;
    
    /**
     * Array que almacena todas las letras que forman la palabra.
     */
    private Label[] letrasPalabra;
    
    /**
     * Método que se ejecuta cuando se pulsa el botón de nuevo juego, reiniciando toda la lógica e interfaz
     * gráfica del mismo.
     * @param event 
     */
    @FXML
    private void accionBotonNuevoJuego(ActionEvent event) {
        reiniciarJuego();
        
    }
    
    /**
     * Método que se ejecuta al pulsar el botón de comprobar la letra introducida y se encarga de actualizar la 
     * interfaz gráfica y enviar la letra a la lógica de juego.
     * @param event 
     */
    @FXML
    private void accionBotonComprobarLetra(ActionEvent event) {   
        comprobarLetra();
    }
    
    /**
     * Método que se ejecuta al pulsar la tecla enter de comprobar la letra introducida y se encarga de actualizar la 
     * interfaz gráfica y enviar la letra a la lógica de juego.
     * @param event 
     */
    @FXML
    public void onEnter(ActionEvent event){
       comprobarLetra();
    }
    
    /**
     * Método que se lanza cuando se carga la interfaz gráfica.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Array con todos los elementos del muñeco
        cuerpo = new Node[]{c_Cabeza, l_Cuerpo, l_BrazoDrcho, l_BrazoIzdo, l_PiernaDrcha, l_PiernaIzda};
        
        // Array con todas las etiquetas donde se va a mostrar la palabra.
        letrasPalabra = new Label[]{lbl_Palabra_01, lbl_Palabra_02, lbl_Palabra_03, lbl_Palabra_04, lbl_Palabra_05,
             lbl_Palabra_06, lbl_Palabra_07, lbl_Palabra_08, lbl_Palabra_09, lbl_Palabra_10};
       
        reiniciarJuego();
    }  
    
    /**
     * Método que comprueba la letra introducida y se encarga de actualizar la 
     * interfaz gráfica y enviar la letra a la lógica de juego.
     */
    private void comprobarLetra(){
        // Comprobamos que exista algún texto en el text field
        if(tf_Letra.getText().length() > 0){
            // Pasamos los datos a la lógica de juego.
            int estado = Ahorcado.jugar(tf_Letra.getText().charAt(0));
            
            // Actualizamos los datos que se muestran.
            char[] letras = Ahorcado.getRespuesta();
            System.out.println(letras);
            for(int i = 0; i < letras.length; i++)
                letrasPalabra[i].setText(String.valueOf(letras[i]));
            
            // Limpiamos el campo de texto.
            tf_Letra.setText("");
            
            // Devolvemos el foco al campo de texto.
            tf_Letra.requestFocus();
            
            // Mostramos una nueva parte del muñeco si ha habido un error.
            if(Ahorcado.getFallos() > 0)
                cuerpo[Ahorcado.getFallos() - 1].setVisible(true);
            
            // Comprobamos si ha ganado o ha perdido.
            if (estado == -1 || estado == 1){
                finalizarJuego(estado);
            }            
        }
    }
    
    
    /**
     * Método que reinicia todos los elementos de la interfaz gráfica para empezar un nuevo juego
     */
    private void reiniciarJuego(){   
        Ahorcado.iniciarJuego();
        
        for(Node i: cuerpo)
            i.setVisible(false);
        
        for(Label l: letrasPalabra)
            l.setText("_");
        
        lbl_Definicion.setText(Ahorcado.getDefinicionPalabraBuscada());
        
        btn_ComprobarLetra.setDisable(false);
        btn_NuevoJuego.setDisable(true);
        tf_Letra.setDisable(false);
    }
    
    /**
     * Finaliza el juego y muestra un mensaje por pantalla.
     * @param haGanado -1 si ha perdido y 1 si ha ganado.
     */
    private void finalizarJuego(int haGanado){
        btn_ComprobarLetra.setDisable(true);
        btn_NuevoJuego.setDisable(false);
        tf_Letra.setDisable(true);
        
        if(haGanado == -1){
            Alert alertaPerder = new Alert(Alert.AlertType.ERROR);
            alertaPerder.setTitle("Perdiste");
            alertaPerder.setHeaderText(null);
            alertaPerder.setContentText("Has perdido. :(");
            alertaPerder.showAndWait();
        }
        else if(haGanado == 1){
            Alert alertaGanar = new Alert(Alert.AlertType.INFORMATION);
            alertaGanar.setTitle("Ganaste");
            alertaGanar.setHeaderText(null);
            alertaGanar.setContentText("¡Muy bien! Has ganado :)");
            alertaGanar.showAndWait();
        }
        
    }
    
}
