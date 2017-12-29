package app.compiladores;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;

/**
 *
 * @author Danny Mencos
 */
public class Ejemplo1 {

    public Ejemplo1(){

    }
    public URL getRuta(){
        return getClass().getResource("entrada.txt");
    }
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Ejemplo1 h=new Ejemplo1();
            System.out.println("iniciando....");

            //parser p = new parser(new Yylex(h.getClass().getResourceAsStream("waren")));
            //p.parse();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
