package app.compiladores.tarea2;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main{
    public static ArrayList <Integer> MERGE(ArrayList <Integer> a,ArrayList <Integer> B)
    {
       int i = 0;
       int j = 0;
        ArrayList A3=new ArrayList();
       while(i < a.size() && j<B.size())
        {
            if(a.get(i) < B.get(j)) {
                A3.add(a.get(i));
                i++;
            }
            else {
                A3.add(B.get(j));
                j++;
            }
        }
        for(int x=i ; x< a.size(); x++)
        {
            A3.add(a.get(x));
        }
        for (int x=j ; x< B.size(); x++)
        {
            A3.add(B.get(x));
        }
        return  A3;
    }
    public static ArrayList  suma(ArrayList <Integer> a1,ArrayList <Integer> a2){
        ArrayList A3=new ArrayList();
        for(int i = 0; i < a1.size()-1; i++)
        {
            int aux =0;
            for (int j = i+1; j < a2.size(); j++)
            {
                aux = aux + a1.get(i)*a2.get(j);
            }
            A3.add(aux);
        }
        return A3;
    }
    public static void  swap(int i,int j,ArrayList <Integer> l){
        int aux = l.get(i);
        l.set(i,l.get(j));
        l.set(j,aux);
    }
    public static ArrayList<Integer> MS (ArrayList <Integer> A)
    {
        ArrayList A1 = new ArrayList();
        ArrayList A2 = new ArrayList();
        if (A.size() <= 1)
            return A;
        else
        {
            for (int i = 0; i < A.size()/2; i++)
            {
                A1.add(A.get(i));
            }
            for (int i = A.size()/2; i < A.size(); i++)
            {
                A2.add(A.get(i));
            }

            A1=MS(A1);
            A2=MS(A2);
            ArrayList res= MERGE(A1,A2);
            return res;
        }
    }
    public static int particion (ArrayList<Integer> l,int ini,int fin)
    {
        int pivot = l.get(0);
        int j = fin;
        int i = ini;

        while(i < j)
        {
            while (i < l.size() && i<j && pivot > l.get(i))
            {
                i++;
            }

            while (j >= 0 && i<j && pivot <= l.get(j))
            {
                j--;
            }
            swap(i,j,l);
        }
        return j-1;
    }
    public static void quick(ArrayList<Integer> l,int ini,int fin){
        if(l.size()<=1)return;
        int p=particion(l,ini,fin);
        if(p==ini-1 || p==fin+1)
            return;
        quick(l,0,p);
        quick(l,p+1,fin);
    }
    public static void burbuja (ArrayList <Integer> l)
    {
        for (int i = 0; i<l.size();i++)
        {
            for (int j = i+1; j <l.size(); j++)
            {
                if (l.get(i) > l.get(j))
                    swap(i,j,l);
            }
        }
    }
    public static void selseccion(ArrayList<Integer>l){
        for (int i =1; i < l.size();i++)
        {
            int j = i;
            int menor = i;
            while (j<l.size())
            {
                if (l.get(menor) > l.get(j))
                    menor =j;
                j++;
            }
            swap(i-1,menor,l);
        }

    }
    public static void shell (ArrayList<Integer>l)
    {
        int k = l.size()/2;
        while(k>0)
        {
            for (int i = 0; i <l.size(); i = i+k)
            {
                int c =i;
                while(c >= 1 && l.get(c) < l.get(c-k) )
                {
                    swap(c,c-k,l);
                    c=c-k;
                }
            }
            k=k/2;
        }

    }
    public static  void insercion    (ArrayList <Integer> l) {
        for (int i = 0; i <l.size(); i++)
        {
            int k =i;
            while(k >= 1 && l.get(k) < l.get(k-1) )
            {
                swap(k,k-1,l);
                k--;
            }
        }
    }
    public static ArrayList<Integer> merge(ArrayList<Integer> a1,ArrayList<Integer> a2){
        ArrayList<Integer>a3=new ArrayList<>();
        for(int i=0;i<a1.size();i++){
            for(int j=0;j<a2.size();j++){
                if(j<a2.size()&&i<a1.size() && a1.get(i)>a2.get(j)){
                    a3.add(a1.get(i));
                    i++;
                }
                if(i<a1.size()&&j<a2.size()&&a1.get(i)>=a2.get(j)){
                    a3.add(a2.get(j));
                    j++;
                }
            }
        }
        return a3;
    }
    public static void main(String argc[]){
        main a=new main();
        ArrayList a1=new ArrayList();
        a1.add(11);
        a1.add(2);
        a1.add(5);
        a1.add(13);
        a1.add(5);
        a1.add(15);
        a1.add(5);

        ArrayList a2=new ArrayList();
        a2.add(1);
        a2.add(5);
        a2.add(7);
        a2.add(9);

        a.quick(a1,0,a1.size()-1);
        System.out.println(Arrays.toString(a1.toArray()));
    }
}
