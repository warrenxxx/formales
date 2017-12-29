package app.paralela;

import app.global.point;

import java.util.ArrayList;

public class flacas extends  Thread{
    public String nombre;
    public Semaforo next_mujer;
    public Semaforo TENGOpERMISO;
    public objetoParalelo p;
    public flacas(String nombre, Semaforo next_mujer, Semaforo TENGOpERMISO,objetoParalelo p) {
        this.nombre = nombre;
        this.next_mujer = next_mujer;
        this.TENGOpERMISO = TENGOpERMISO;
        this.p=p;
    }

    @Override
    public void run() {
        try {

            TENGOpERMISO.P();
            next_mujer.P();
            if(p!=null) {
                irAlBaño(p);
            }else{
                System.out.println("flaca entrar al baño ");
                Thread.sleep(3000);
                System.out.println( );

            }
            next_mujer.V();
            if(p!=null) {
                salirDelBaño(p);
            }else{
                System.out.println("flaca saliendo del baño");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void irAlBaño(objetoParalelo p){
        if(p==null)return;

        ArrayList l=new ArrayList();
        l.add(new point(150,0));
        l.add(new point(0,-50));
        p.ruta(l);
    }
    public void salirDelBaño(objetoParalelo p){
        if(p==null)return;

        ArrayList l=new ArrayList();
        l.add(new point(150,0));
        l.add(new point(0,-50));
        p.ruta(l);
    }
}
