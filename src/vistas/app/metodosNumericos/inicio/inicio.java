package app.metodosNumericos.inicio;

import app.global.fml;
import app.metodosNumericos.matrices;
import app.metodosNumericos.matris.Matris;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class inicio extends Application {

    @FXML
    Button button;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public Parent createContent(){
        GridPane p=new GridPane();

        fml j=new fml("../matris/Matris.fxml",getClass());
        Matris g= (Matris) j.getController();
        g.setName("Matris 1");

        fml j2=new fml("../matris/Matris.fxml",getClass());
        Matris g2= (Matris) j2.getController();
        g2.setName("Matris 2");

        fml j3=new fml("../matris/Matris.fxml",getClass());
        Matris g3= (Matris) j3.getController();
        g3.setName("Matris Resultado");

        ComboBox b=new ComboBox();
        b.setItems(FXCollections.observableArrayList("SUMA","RESTA","MULTIPLICACION","DIVICION"));
        b.getSelectionModel().select(0);
        Button run=new Button("Go");

        run.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("error en columnas o filas");
            alert.setContentText("el numero de columnas o filas es incorrecto");

            matrices aa=g.getMatris();
            matrices bb=g2.getMatris();
            matrices cc=g3.getMatris();
            if(b.getSelectionModel().getSelectedIndex()==0){
                g3.setmatris(aa.suma(bb));
            }
            if(b.getSelectionModel().getSelectedIndex()==1){
                g3.setmatris(aa.resta(bb));
            }
            if(b.getSelectionModel().getSelectedIndex()==2){
                g3.setmatris(aa.multiplicacion(bb));
            }
            if(b.getSelectionModel().getSelectedIndex()==3){
                g3.setmatris(aa.divicion(bb));
            }
            if(b.getSelectionModel().getSelectedIndex()==3){
                g3.setmatris(aa.divicion(bb));
            }
            if(b.getSelectionModel().getSelectedIndex()==3){
                g3.setmatris(aa.divicion(bb));
            }
        });

        VBox v=new VBox();
        v.getChildren().add(b);
        v.getChildren().add(run);


        p.add(j.getParent(),0,0);
        p.add(j2.getParent(),1,0);
        p.add(j3.getParent(),0,1);
        p.add(v,1,1);

        return p;
    }
}
