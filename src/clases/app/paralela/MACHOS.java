package app.paralela;

import app.global.point;

import java.util.ArrayList;

public class MACHOS extends  Thread{
    public String nombre;
    public Semaforo next_hombre,
                    mutex,
                    DandoPermisoAlASfLACAS;
    public app.paralela.baño baño;
    public objetoParalelo p;
    public MACHOS(String nombre, Semaforo next_hombre, Semaforo mutex, Semaforo dandoPermisoAlASfLACAS, baño baño,objetoParalelo p) {
        this.nombre = nombre;
        this.next_hombre = next_hombre;
        this.mutex = mutex;
        DandoPermisoAlASfLACAS = dandoPermisoAlASfLACAS;
        this.baño = baño;
        this.p=p;
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
    @Override
    public void run() {
        try {
            next_hombre.P();
            mutex.P();
            baño.cuantas_patasahy++;
            mutex.V();
            if(p!=null ) {
                p.descansar(1000);
                irAlBaño(p);
            }
            else {
                System.out.println("iendo al baño");
                Thread.sleep(3000);
                System.out.println("salir del baño");
            }next_hombre.V();
            salirDelBaño(p);
            mutex.P();
            baño.cuantas_patasahy--;
            if(baño.cuantas_patasahy==0){
                this.DandoPermisoAlASfLACAS.V();
            }
            mutex.V();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
