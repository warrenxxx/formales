package app.metodosNumericos;

import app.metodosNumericos.matris.Matris;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class matrices {
    public double matris[][];
    public int m,n;
    public matrices(int m,int n ){
        this.m=m;
        this.n=n;
        this.matris=new double[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                matris[i][j]=0;
    }
    public matrices(int m,int n,double mat[][]){
        this.m=m;
        this.n=n;
        this.matris=mat;
    }

    public matrices suma(matrices a){
        double res[][]=new double[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[i][j]=a.matris[i][j]+this.matris[i][j];
            }
        }
        return new matrices(m,n,res);
    }
    public matrices resta(matrices a){
        double res[][]=new double[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[i][j]=-a.matris[i][j]+this.matris[i][j];
            }
        }
        return new matrices(m,n,res);
    }
    public matrices multiplicacion(matrices a){
        double res[][]=new double[m][a.n];

        for(int i=0;i<m;i++){
            for(int j=0;j<a.n;j++){
                for(int k=0;k<n ;k++){
                    res[i][j]=res[i][j]+this.matris[i][k]*a.matris[k][j];
                }
            }
        }
        return new matrices(m,a.n,res);
    }
    public matrices unitario(int m){
        double res[][]=new double[m][m];
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                if(i==j)                    res[i][j]=1;
                else                        res[i][j]=0;
            }
        }
        return new matrices(m,m,res);
    }
    public void multipliRowConstant(double k,int c){

        for(int i=0;i<n;i++){
             matris[c][i]=matris[c][i]*k;
        }

    }
    public matrices sumarRowConstant(double k,int c){
        double res[][]=new double[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[i][j]=matris[i][j];
                if(i==c)
                    res[i][j]=res[i][j]-k;
            }
        }
        return new matrices(m,m,res);
    }
    public matrices inversa(){
        matrices res=new matrices(m,m);
        res=res.unitario(m);

        for(int i=0;i<m;i++){
            double tmp=matris[i][i];
            multipliRowConstant(1/tmp,i);
            for(int j=0;j<m;j++){
                if(i!=j){
                    for(int k=0;k<n;k++){
                        res.matris[j][k]=matris[i][k]*matris[j][k]-res.matris[j][k];
                    }
                }
            }
            mos();
        }
        return res;
    }
    public double ll(){
        for(int i=0;i<m;i++){
            double tmp=matris[i][i];
            multipliRowConstant(1/tmp,i);
            for(int j=i;j<m;j++){
                if(i!=j){
                    double tmp2=matris[j][i];
                    for(int k=0;k<n;k++){
                        matris[j][k]=matris[i][k]*tmp2-matris[j][k];
                    }
                }
            }
        }
        double res=0;
        for(int i=0;i<m;i++){
            res*=matris[i][i];
        }
        return res;
    }
    public double lu(){
        for(int i=m-1;i>=0;i--){
            double tmp=matris[i][i];
//            multipliRowConstant(1/tmp,i);

            for(int j=i;j>=0;j--){
                if(i!=j){
                    double tmp2=matris[j][i]/tmp;
                    for(int k=0;k<n;k++){
                        matris[j][k]=matris[i][k]*tmp2-matris[j][k];
                    }
                }
            }
        }
        double res=0;
        for(int i=0;i<m;i++){
            res+=matris[i][i];
        }
        return res;
    }
    public double analisar(double a,double b){
        return Math.pow(a,b)-a*b+a/b+a*Math.pow(2,b);
    }
    public matrices clone(){
        double h[][]=new double[m][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                h[i][j]=matris[i][j];
        return new matrices(n,m,h);
    }
    public matrices divicion(matrices a){
        return multiplicacion(a.inversa());
    }
    public void mos(){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
                System.out.print(matris[i][j]+" ");
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String argc[]){
        matrices m=new matrices(3,3,new double[][]{{2,1,-3},{-1,3,2},{3,1,-3}});
        m.inversa();
    }
}
