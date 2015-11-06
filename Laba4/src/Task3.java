    import javax.swing.*;
    import java.awt.*;
    import java.text.DecimalFormat;
    import java.text.NumberFormat;
    import java.util.*;

    /**
     * Created by panikun on 26.10.15.
     */
    public class Task3 extends Thread{
        public static final Color BACKGROUND_COLOR = Color.WHITE;
        public static final Color AXIS_COLOR = Color.BLACK;
        public static final Color KONTUR_COLOR = new Color(0x006400);
        public static final int POINT_RADIUS = 4;
        public static void main(String[] args){

                SwingUtilities.invokeLater(new Task3());

            }
            @Override
            public void run() {
                new Window();
        }
    }
    class Ponto extends JComponent implements Comparable{
        double X;
        double Y;
        int realX;
        int realY;
        int radius;
        Color color;
        JTextArea textArea;
        boolean isInKontur;
        Thread animation;
        Ponto(double x, double y, int rx, int ry, JTextArea textArea){
            this.radius= Task3.POINT_RADIUS;
            this.Y=y;
            this.X=x;
            this.realX = rx;

            this.realY = ry;
            this.textArea = textArea;
            this.textArea.setText(this.toString());
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

        public void setBlueColor(){this.color = Color.BLUE;}
        public void setRedColor(){this.color = Color.RED;}
/*        public void setNewRealCoords(int x, int y){
            this.realX = x;
            this.realY = y;
        }*/
        public void setRadius(int radius){
            this.radius=radius;
        }

        @Override
        protected void paintComponent(Graphics g){
            g.setColor(color);
            this.setBounds(realX - this.radius / 2, realY - this.radius / 2, this.radius, this.radius);
            this.textArea.setBounds(realX - this.radius, realY - this.radius, 70, 20);
            g.fillOval(this.getWidth() / 2 - radius / 2, this.getHeight() / 2 - radius / 2,
                    radius, radius);
        }
    @Override
    public String toString(){
        NumberFormat formatter = new DecimalFormat("#0.00");
        return ("{"+ formatter.format(this.X)+", "+formatter.format(this.Y)+"}");
    }

    @Override
    public boolean equals(Object obj) {
        Ponto tmp = null;
        try{
            tmp = (Ponto)obj;
        }
        catch (ClassCastException e){
            return false;
        }
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
    }
}