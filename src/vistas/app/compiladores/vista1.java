package app.compiladores;

import app.compiladores.tarea2.VistaGramatica;
import app.global.fml;
import app.metodosNumericos.matris.Matris;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class vista1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
        public void start(Stage primaryStage) {
        fml j=new fml("tarea2/vistaGramatica.fxml",getClass());
        VistaGramatica g= (VistaGramatica) j.getController();


        Scene root=new Scene(j.getParent());
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
