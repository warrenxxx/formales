package app.metodosNumericos;

public class metodos {
    public double x;

    public metodos(double x) {
        this.x = x;
    }
    public double f(double x){
        return x*x*x-7.5833*x*x+16.9444*x-11.5;
    }
    public double f1(double x){
        return 3*x*x-2*7.5833*x+16.9444;
    }
    public double f2(double x){
        return 4*x-2*7.5833;
    }
    public  void biseccion(double a0,double b0,double tolerancia){

        double a=a0,b=b0;
        while (true){
            double c=(a+b)/2;
            if(f(c)*f(b)<0)
                b=c;
            else
                a=c;

            if((b-a)/2<tolerancia ||f(c)==0){
                break;
            }
        }
    }
    public  void reglafalsa(double xi,double xs,double es){
        double eai=2*es,
                i=0,xra=0;

        while(eai>es){
            i++;
            double xr=((f(xs)*xi)-(f(xi)*xs))/(f(xs)-f(xi));
            if(i==1){
                System.out.println(i+" "+xr);
            }else{
                eai=Math.abs((xr-xra)/xr)*100;
                System.out.println(i+" "+xr+" "+eai);
            }
            if(f(xi)*f(xr)<0){
                xs=xr;
            }else{
                xi=xr;
            }
//            System.out.println(eai+" "+es);
            xra=xr;
        }
    }
    public void nRS(double xi,double es){
        double eai=2*es,
                h=0.00001;
        double i=0;
        while(eai>es){
            i=i+1;
            double xi1=xi-f(xi)/f1(xi);
            if(i>1){
                eai=(Math.abs(xi1-xi)/xi1)*100;
                System.out.println(xi1+" "+eai);
            }else{
                System.out.println(i+" "+xi1);
            }
            xi=xi1;
        }
    }
    public void nRSM(double xi,double es){
        double eai=2*es,
                h=0.00001;
        double i=0;
        while(eai>es){
            i=i+1;
            double xi1=xi-(f(xi)*f1(xi))/(f1(xi)*f1(xi)-f(xi)*f2(xi));
            if(i>1){
                eai=(Math.abs(xi1-xi)/xi1)*100;
                System.out.println(xi1+" "+eai);
            }else{
                System.out.println(i+" "+xi1);
            }
            xi=xi1;
        }
    }
    public static void main(String argc[]){
        metodos f=new metodos(4);
        f.nRSM(5,0.001);
    }
}
