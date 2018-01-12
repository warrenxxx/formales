package app.compiladores.tarea2;

import java.util.ArrayList;
import java.util.Comparator;

public class listapar extends ArrayList<par> {
    public boolean existe(String x){
        return this.indexOf(x)!=-1;

    }

}
