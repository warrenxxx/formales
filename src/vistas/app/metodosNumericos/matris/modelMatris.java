package app.metodosNumericos.matris;

import javafx.beans.property.*;

public class modelMatris {
    private final StringProperty num0;
    private final StringProperty num1;
    private final StringProperty num2;
    private final StringProperty num3;
    private final StringProperty num4;
    private final StringProperty num5;
    private final StringProperty num6;
    private final StringProperty num7;


    public modelMatris() {
        this.num0 = new SimpleStringProperty("0");
        this.num1 = new SimpleStringProperty("0");
        this.num2 = new SimpleStringProperty("0");
        this.num3 = new SimpleStringProperty("0");
        this.num4 = new SimpleStringProperty("0");
        this.num5 = new SimpleStringProperty("0");
        this.num6 = new SimpleStringProperty("0");
        this.num7 = new SimpleStringProperty("0");

    }


    public StringProperty num0Property() { return num0; }
    public StringProperty num1Property() { return num1; }
    public StringProperty num2Property() { return num2; }
    public StringProperty num3Property() { return num3; }
    public StringProperty num4Property() { return num4; }
    public StringProperty num5Property() { return num5; }
    public StringProperty num6Property() { return num6; }
    public StringProperty num7Property() { return num7; }

    public double getitem(int a){
        if(a==0)return Double.parseDouble(this.num0.getValue());
        if(a==1)return Double.parseDouble(this.num1.getValue());
        if(a==2)return Double.parseDouble(this.num2.getValue());
        if(a==3)return Double.parseDouble(this.num3.getValue());
        if(a==4)return Double.parseDouble(this.num4.getValue());
        if(a==5)return Double.parseDouble(this.num5.getValue());
        if(a==6)return Double.parseDouble(this.num6.getValue());
        return Double.parseDouble(this.num7.getValue());

    }
    public void setitem(int a,double k){
        if(a==0)this.num0.setValue(String.valueOf(k));
        if(a==1)this.num1.setValue(String.valueOf(k));
        if(a==2)this.num2.setValue(String.valueOf(k));
        if(a==3)this.num3.setValue(String.valueOf(k));
        if(a==4)this.num4.setValue(String.valueOf(k));
        if(a==5)this.num5.setValue(String.valueOf(k));
        if(a==6)this.num6.setValue(String.valueOf(k));
        if(a==7)this.num7.setValue(String.valueOf(k));

    }
}
