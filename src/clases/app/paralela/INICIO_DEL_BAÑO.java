package app.paralela;

public class INICIO_DEL_BAÑO {
    public static void main(String argc[]){
        Semaforo mutex=new Semaforo(1),
                 permisoFlacas=new Semaforo(0),
                 nex_Hombre=new Semaforo(5),
                 next_Flaca=new Semaforo(1);
        baño baño=new baño(0,0);

        MACHOS m[]=new MACHOS[10];
        flacas f[]=new flacas[10];

        for(int i=0;i<10;i++){
            m[i]=new MACHOS("m"+i,nex_Hombre,mutex,permisoFlacas,baño,null);
            f[i]=new flacas("f"+i,next_Flaca,permisoFlacas,null);
        }

        m[0].start();
        m[1].start();
        f[2].start();
        m[3].start();

    }
}
