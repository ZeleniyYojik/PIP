import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by panikun on 26.10.15.
 */
public class Task3 extends Thread{
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color AXIS_COLOR = Color.BLACK;
    public static final Color KONTUR_COLOR = new Color(0x006400);
    public static void main(String[] args){

            SwingUtilities.invokeLater(new Task3());

        }
        @Override
        public void run() {
            new Window();
    }
}
class Ponto implements Comparable{
    double X;
    double Y;
    Ponto(double x, double y){
        this.Y=y;
        this.X=x;
    }
    public int compareTo(Object obj){
        Ponto tmp = (Ponto)obj;
        if((Math.pow(this.X,2)+Math.pow(this.Y,2))>(Math.pow(tmp.X,2)+Math.pow(tmp.Y,2))){
            return 1;
        }
        if((Math.pow(this.X,2)+Math.pow(this.Y,2))==(Math.pow(tmp.X,2)+Math.pow(tmp.Y,2))) {
        return 0;
        }
        else{
         return -1;
         }
    }
    @Override
    public String toString(){
        return ("{"+this.X+", "+this.Y+"}");
    }

    @Override
    public boolean equals(Object obj) {
        Ponto tmp = (Ponto)obj;
        if (tmp==null){
            return false;
        }
        if (this.X==tmp.X && this.Y==tmp.Y){
            return true;
        }
        return false;
    }
}

class Kontur{
    double R;
    Kontur(double r) {
        this.R = r;
    }

    public boolean isInKontur(Ponto point){
        if (Math.abs(point.Y)>R) {
            return false;
        }
        //Попадание в первую четверть
        if (point.Y>=0 && point.X>=0 && point.Y<=(this.R-point.X)) {
            return true;
        }
        //Попадание в четвертую четверть
        if (point.Y<=0 && point.X>=0 && Math.sqrt(Math.pow(point.X,2)+Math.pow(point.Y,2))<=this.R) {
            return true;
        }
        //Попадание в третью четверть
        if (point.Y<=0 && point.X<=0 && point.Y>=(-this.R) && point.X>=(-this.R/2)) {
            return true;
        }
        return false;
    }

    public void setRadius(double rad){
        this.R = rad;
    };
}