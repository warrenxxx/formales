package app.compiladores.tarea2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

public class gramatica {
    private HashMap<Character,ObservableList<String>>map;

    public gramatica() {
        this.map=new HashMap<>();
    }
    public void add(Character c,String s){
        if(this.map.get(c)==null)
            this.map.put(c,FXCollections.observableArrayList());
        this.map.get(c).add(s);
    }
    public ObservableList primero(Character x){
        if(map.get(x)==null)
            return FXCollections.observableArrayList(x);

        ObservableList res=FXCollections.observableArrayList();
        map.get(x).forEach((e)->{
            Character c=e.charAt(0);
            if(c>='a'&&c<='z')
                res.add(c);
            else
              unir(res,primero(c));

        });
        return res;
    }
    public ObservableList follow(Character x){
        if(map.get(x)==null)
            return FXCollections.observableArrayList(x);

        ObservableList res=FXCollections.observableArrayList();
        map.get(x).forEach((e)->{
            Character c=e.charAt(0);
            if(c>='a'&&c<='z')
                res.add(c);
            else
                unir(res,primero(c));

        });
        return res;
    }
    public void unir(ObservableList a,ObservableList b){
        b.forEach(e->{
            if(a.indexOf(e)==-1)a.add(e);
        });
    }
    public static void main(String argc[]){
        gramatica p=new gramatica();
        p.add('A',"BCD");
        p.add('B',"xG");
        p.add('C',"y");
        p.add('D',"xy");
        p.add('A',"zxy");
        System.out.println(Arrays.toString( p.primero('A').toArray()));
    }

}
