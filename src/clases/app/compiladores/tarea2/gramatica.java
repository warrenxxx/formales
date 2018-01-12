package app.compiladores.tarea2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.*;

public class gramatica {
    ArrayList ter;
    ArrayList<String> not;
    HashMap<String,List<List<String>>> gram;
    public gramatica(ArrayList<par> par) {
        gram=new HashMap<>();
        par.sort(new Comparator<app.compiladores.tarea2.par>() {
            @Override
            public int compare(par o1, par o2) {
                if(o1.key.length()>o2.key.length())
                    return -1;
                if(o1.key.length()<o2.key.length())
                    return 1;
                return 0;
            }
        });
        ter=new ArrayList();
        not=new ArrayList();
        par.forEach(e->{
            if(not.indexOf(e.key)==-1){
                not.add(e.key);
                gram.put(e.key,new ArrayList<>());
            }
        });
        par.forEach(e->{
            String h[]=e.value.split("|");
            for (String m:h){
                gram.get(e.key).add(convert(m, toArrayString(not.toArray()) ));
            }
        });
    }
    public String[] toArrayString(Object []o){
        String res[]=new String[o.length];
        for(int i=0;i<o.length;i++)
            res[i]=o[i].toString();
        return res;
    }
    public static ArrayList splitToList(String x,String y){
        String aux[]=x.split(y);
        ArrayList res=new ArrayList();
        res.add(aux[0]);
        for(int i=1;i<aux.length;i++){
            res.add(y);
            res.add(aux[i]);
        }
        return res;
    }
    public ArrayList convert(String x,String y[]){
        ArrayList<String> l=new ArrayList();
        l.add(x);
        for(String c:y){
            ArrayList tmp=new ArrayList();
            for(String m:l){
                if(buscar(m,y)!=-1) {
                    continue;
                }
                ArrayList<String> u=splitToList(m,c);
                for(String item:u){
                    tmp.add(item);
                }
            }
            l=tmp;
        }
        ArrayList temp=new ArrayList();
        for(String item:l){
            if(buscar(item,y)==-1){
                String sp[]=item.split("");
                for(String ad:sp)temp.add(ad);
            }else{
                temp.add(item);
            }
        }
        return temp;
    }
    public static int buscar(String x,String y[]){
        for(int i=0;i<y.length;i++)
            if(x.compareTo(y[i])==0)return i;
        return -1;
    }
    public static void main(String argc[]){
        ArrayList<par> par=new ArrayList<>();
        par.add(new par("E","TE'"));
        par.add(new par("E'","+TE'|-TE'|L"));
        par.add(new par("T","FT'"));
        par.add(new par("T'","*FT'|/FT'|L"));
        par.add(new par("F","(E)|NUM|id"));
        gramatica g=new gramatica(par);
        g.gram.forEach((k,v)->{
            System.out.println(k);
            v.forEach(e->{
                System.out.println("----"+Arrays.toString(e.toArray()));
            });
        });

    }

}
