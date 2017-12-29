package app.global;

public class point {
    public double x,y;
    public point(double x ,double y){
        this.x=x;
        this.y=y;
    }
    public point suma(point a){
        return new point(a.x+this.x,a.y+this.y);
    }
    public point resta(point a){
        return new point(-a.x+this.x,-a.y+this.y);
    }
    public point v(point a){
        return a.resta(this);
    }

}
