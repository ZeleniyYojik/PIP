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

        public void recalculation(double R, double scale, int xCenter,int yCenter){
            int rX = xCenter+(int)((this.X*scale*20)/R);
            int rY = yCenter-(int)((this.Y*scale*20)/R);
//            if (rX<0 || rY<0){this.setVisible(false);this.textArea.setVisible(false); return;}
//            if (rX>xCenter*2 || rY>yCenter*2){this.setVisible(false);this.textArea.setVisible(false); return;}
//
            this.realX=rX;
            this.realY=rY;
//            this.setVisible(true);
//            this.textArea.setVisible(true);
        }

        public int compareTo(Object obj){
            Ponto tmp = (Ponto)obj;
            return (int)((this.X*this.X+this.Y*this.Y)-(tmp.X*tmp.X+tmp.Y*tmp.Y));
        }

        public void setBlueColor(){this.color = Color.BLUE;}
        public void setRedColor(){this.color = Color.RED;}
        public void setRadius(int radius){
            if (radius<=0){
                this.radius=1;
            }
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
        if (point.Y<=0 && point.X>=0 && Math.sqrt(point.X*point.X+point.Y*point.Y)<=this.R) {
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