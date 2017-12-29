package app.global;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.InputStream;

public class fml {
    private String url;
    private final Class clase;
    private final FXMLLoader loader;
    private Parent parent;
    private Initializable controller;


    public fml(String url,Class clase) {
        this.url = url;
        this.clase=clase;
        this.loader=new FXMLLoader();
        try {
            act();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUrl(String url)  {
        this.url=url;
        try {
            act();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void act() throws IOException {
        InputStream in = clase.getResourceAsStream(url);
        System.out.println(clase.getResource("url"));
        System.out.println(clase.getResourceAsStream(url));
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(clase.getResource(url));
         try {
            this.parent = (Parent) loader.load(in);
        } finally {
            in.close();
        }
        this.controller= (Initializable) loader.getController();
    }
    public Initializable getController(){
        return  controller;
    }

    public Parent getParent() {
        return parent;
    }
}

