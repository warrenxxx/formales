package app.paralela;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.InputStream;

public class paralela1 extends Application {
    Departamento departamento;

    public static void main(String[] args) {
        launch(args);
    }
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ComboBox<String> b=new ComboBox(FXCollections.observableArrayList(
                "HOMBRES",
                "MUJERES"
        ));
        Pair<Initializable,Parent> h=replaceSceneContent("departamento.fxml");

        departamento = (Departamento) h.getKey();

        b.valueProperty().addListener((e,o,n)->{
            if(n.compareTo("\"HOMBRES\"")==0)
                this.departamento.hombres=true;
            else this.departamento.hombres=false;
        });

        GridPane root = new GridPane();

        root.add(b,0,0);
        scene = new Scene(root, 883, 525);


        root.add(h.getValue(),0,2);
        primaryStage.setTitle("Tareas de Compiladores");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private Pair<Initializable,Parent> replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));

        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }

        return  new Pair((Initializable) loader.getController(),page);
    }
}
