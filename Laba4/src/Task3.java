import java.util.*;

/**
 * Created by panikun on 26.10.15.
 */
public class Task3{
    public static void main(String[] args) {

        Set points = new TreeSet();
        //
        points.add(new Ponto(0, -2));
        points.add(new Ponto(4,  3));
        points.add(new Ponto(3,  4));
        points.add(new Ponto(1, 2));
        points.add(new Ponto(2, 1));
        points.add(new Ponto(-3, -5));
        points.add(new Ponto(-5, 4));
        points.add(new Ponto(0, -1));
        points.add(new Ponto(0, 0));

        if (args.length==0) {
            System.out.println("Не задан параметр R");
            System.exit(1);
        }
        Kontur kontur = null;
        try{
            kontur = new Kontur(new Double(args[0]));
        }
        catch(NumberFormatException e){
            System.out.println("Не удалось преобразовать R в число");
            System.exit(1);
        }
        if (kontur.R<=0) {
            System.out.println("Некорректное значение R");
            System.exit(1);
        }

        System.out.println("R="+args[0]);

        for (Object tmp:points) {

            System.out.println(tmp.toString()+" "+(kontur.isInKontur((Ponto)(tmp)) ? "входит в область":"не входит в область"));
        }
    }
}
class Ponto implements Comparable{
    float X;
    float Y;
    Ponto(float x, float y){
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
}