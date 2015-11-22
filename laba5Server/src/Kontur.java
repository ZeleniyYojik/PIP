/**
 * Created by panikun on 22.11.15.
 */
public class Kontur {
    private float r;
    Kontur(float r){
        this.r = r;
    }
    public float getR() {
        return  this.r;
    }

    public int isInKontur(Point point) {

        if (Math.abs(point.getY()) > this.r) {
            return 0;
        }
        //Попадание в первую четверть
        if (point.getY() >= 0 && point.getX() >= 0 && point.getY() <= (this.r - point.getX())) {
            return 1;
        }
        //Попадание в четвертую четверть
        if (point.getY() <= 0 && point.getX() >= 0 && Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()) <= this.r) {
            return 1;
        }
        //Попадание в третью четверть
        if (point.getY() <= 0 && point.getX() <= 0 && point.getY() >= (-this.r) && point.getX() >= (-this.r / 2)) {
            return 1;
        }
        return 0;
    }
}
