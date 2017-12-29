package app.compiladores;

import java.util.ArrayList;

public class praticas {
    public ArrayList burbuja(ArrayList<Integer> l){
        for(int i = 0; i < l.size()-1;i++)
        {
           if(l.get(i)>l.get(i+1))
               swap(l,i,i+1);
        }
        mostrar(l);
        return l;
    }
    public void swap(ArrayList <Integer>l,int i,int j){
        int aux = l.get(i);
        l.set(i,l.get(j));
        l.set(j, aux);
    }

    public int p2(ArrayList<Integer>a,ArrayList<Integer>b){
        ArrayList <Integer> c = new ArrayList<>();

        return 1;
    }
    public int fibonacci (int n)
    {

        ArrayList <Integer> A = new ArrayList<>();
        A.add(1);
        A.add(1);
        int f=0;
        for (int i =2; i<=n;i++)
        {
            A.add(A.get(i-1)+A.get(i-2));
            f = A.get(i)+f;
        }
        return f+2;

    }

    public boolean esprimo(int a){
        int p = 0;
        for (int i = 1; i <= a; i++){
            if (a % i == 0)
                p++;
        }
        if(p>2)return false;
        else return true;
    }
    public void mostrar(ArrayList l){
        for(int i=0;i<l.size();i++)
            System.out.print(l.get(i)+" ");
        System.out.println();
    }
    public static void main(String argc[]){
        ArrayList l=new ArrayList();
        l.add(2);
        l.add(3);
        l.add(3);
        l.add(3);
        l.add(9);

        ArrayList l2=new ArrayList();
        l2.add(1);
        l2.add(4);
        l2.add(8);

        praticas p=new praticas();
        p.p2(l,l2);
    }
}
