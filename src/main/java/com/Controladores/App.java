package com.Controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Método que permite cargar y mostrar una escena.
     * @param stage escena que se ha de mostrar.
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/com/vistas/Principal"));
        stage.setScene(scene);
        stage.setTitle("Juego del ahorcado");
        stage.show();
    }

    /**
     * Método que interpreta un FXML para crear una escena a partir de él.
     * @param fxml ruta en la que se encuentra el FXML y nombre del archivo.
     * @return devuelve el objeto listo para poder mostrarse
     * @throws IOException 
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método principal.
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }

}