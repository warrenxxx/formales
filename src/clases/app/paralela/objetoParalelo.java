package app.paralela;


import app.global.point;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class objetoParalelo{
    private final String name;
    private final Rectangle objeto;
    private int time=2000;

    public objetoParalelo(double x, double y,double width,double height, String name) {
        this.objeto=new Rectangle((int)x,(int)y,(int)width,(int)height);
        this.name = name;
        System.out.println(name);
        if(this.name.compareTo("hombre")==0){
            this.objeto.setFill(Color.BLUE);
        }else{
            this.objeto.setFill(Color.RED);
        }
    }


    public void mover(point b){
//        hilo x=new hilo(b,objeto);
  //      x.start();
        TranslateTransition t=new TranslateTransition(Duration.millis(time),objeto);
        t.setByX(b.x);
        t.setByY(b.y);
        t.play();
    }
    public void vuelta(double angulo){
        RotateTransition t=new RotateTransition(Duration.millis(time),objeto);
        t.setByAngle(angulo);
        t.play();
    }
    public void vueltaYandar(double angulo,point b){
        TranslateTransition t1=new TranslateTransition(Duration.millis(time),objeto);
        t1.setByX(b.x);
        t1.setByY(b.y);

        RotateTransition t=new RotateTransition(Duration.millis(time),objeto);
        t.setByAngle(angulo);

        t.setOnFinished(e->{
            t1.play();
        });
        t.play();
    }
    public void ruta(ArrayList l){
        ruta(l,0);
    }
    public void descansar(int time){
        hiloDescansar t=new hiloDescansar(this.objeto,time);
        t.start();
    }
    public void ruta(ArrayList<point> puntos ,int k){
        System.out.println("ww"+puntos.size()+" "+k);
        if(k>=puntos.size())
            return;
        TranslateTransition t=new TranslateTransition(Duration.millis(time),objeto);
        t.setByY(puntos.get(k).y);
        t.setByX(puntos.get(k).x);
        t.setOnFinished(e->{
            ruta(puntos,k+1);
        });
        t.play();
    }
    public Rectangle getObjeto() {
        return objeto;
    }
}
class hiloDescansar extends Thread{
    private final Rectangle objetjo;
    private final int time;
    public hiloDescansar(Rectangle objetjo,int time) {
        this.objetjo = objetjo;
        this.time=time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class hilo extends Thread{
    private final point b;
    private final Rectangle objeto;

    public hilo( point b, Rectangle objeto) {
        this.b = b;
        this.objeto = objeto;
    }
    @Override
    public void run() {
        point a=new point(this.objeto.getX(),this.objeto.getY());
        point vector=b.resta(a);
        double m=(vector.y/vector.x);
        for (double i=a.x;i<b.x;i++){
            this.objeto.setX((int)i);
            this.objeto.setY(i*m);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
