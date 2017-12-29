package app.paralela;

import app.global.point;
import app.paralela.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Departamento implements Initializable{
    boolean hombres=true;
    @FXML
    Pane lienso;
    Semaforo mutex=new Semaforo(1),
            permisoFlacas=new Semaforo(0),
            nex_Hombre=new Semaforo(5),
            next_Flaca=new Semaforo(1);
    app.paralela.baño baño=new baño(0,0);
    public void initialize(URL location, ResourceBundle resources) {
        lienso.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if(hombres==true){
                objetoParalelo x=crear(new point(event.getX(),event.getY()),50,50,true);
                MACHOS mm=new MACHOS("hombre"+hombre,nex_Hombre,mutex,permisoFlacas,baño,x);
                mm.start();
            }
            else{
                objetoParalelo x=crear(new point(event.getX(),event.getY()),50,50,false);
                flacas mm=new flacas("f"+mujer,next_Flaca,permisoFlacas,x);
                mm.start();
            }

        });

    }
    int hombre=0;
    int mujer=0;
    public objetoParalelo crear(boolean esHombre){
        if(esHombre) {
            objetoParalelo p = new objetoParalelo(10, 200, 50, 50, "hombre");
            MACHOS x=new MACHOS("hombre"+hombres,nex_Hombre,mutex,permisoFlacas,baño,p);
            this.lienso.getChildren().add(p.getObjeto());
            hombre++;
            return p;
        }else{
            objetoParalelo p = new objetoParalelo(10, 200, 50, 50, "flaca");
            this.lienso.getChildren().add(p.getObjeto());
            return p;
        }
    }
    public objetoParalelo crear(point a,double w,double h,boolean pp){
        if(pp) {
            objetoParalelo p = new objetoParalelo(10, 200, 50, 50, "hombre");
            MACHOS x=new MACHOS("hombre"+hombres,nex_Hombre,mutex,permisoFlacas,baño,p);
            this.lienso.getChildren().add(p.getObjeto());
            hombre++;
            return p;
        }else{
            objetoParalelo p = new objetoParalelo(10, 200, 50, 50, "flaca");
            this.lienso.getChildren().add(p.getObjeto());
            return p;
        }    }


}
