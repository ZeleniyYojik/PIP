import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.util.*;

import static java.lang.Thread.*;

/**
 * Created by panikun on 29.10.15.
 */
public class Graphic extends JPanel{

    private Set<Ponto> points = new HashSet<Ponto>();
    private Kontur kontur;
    private int xCenter;
    private int yCenter;
    private int x;
    private int y;
    private int height;
    private int width;
    private int scale;
    public Graphic() {
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBackground(Task3.BACKGROUND_COLOR);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPoint(e.getX(), e.getY());
            }
        });
        kontur = new Kontur(1);
        repaint();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    public void addPoint(int x, int y){
        double Xc =(double)(x-xCenter)*this.kontur.R/(scale*20);
        double Yc = (double)(yCenter-y)*this.kontur.R/(scale*20);
        JTextArea area = new JTextArea();
        area.setBounds(x - Task3.POINT_RADIUS / 2, y - Task3.POINT_RADIUS / 2, 70, 20);
        area.setBackground(new Color(0, 0, 0, 0));
        area.setFont(new Font(area.getFont().getFontName(), area.getFont().getStyle(), area.getFont().getSize() - 3));
        Ponto point = new Ponto(Xc,Yc,x,y,area);
        point.setBounds(x - Task3.POINT_RADIUS / 2, y - Task3.POINT_RADIUS / 2, Task3.POINT_RADIUS, Task3.POINT_RADIUS);
        if (!this.points.contains(point)) {

            this.points.add(point);
            this.add(point);
            this.add(area);
        }
        repaint();
    }
    public void addPoint(double x, double y){
        int Xc = xCenter+(int)((x*scale*20)/this.kontur.R);
        int Yc = yCenter-(int)((y*scale*20)/this.kontur.R);
        boolean isVisible=true;
        if (Xc < 0 || Yc < 0 || Yc > xCenter * 2 || Yc > yCenter * 2) {
            isVisible=false;
            for (int i = (int)this.kontur.R; i<=20;i++) {
                Xc = xCenter+(int)((x*scale*20)/i);
                Yc = yCenter-(int)((y*scale*20)/i);
                if (Xc > 0 && Yc > 0 && Yc < xCenter * 2 && Yc < yCenter * 2) {
                    break;
                }

            }
        }
        JTextArea area = new JTextArea();
        area.setBounds(Xc - Task3.POINT_RADIUS / 2, Yc - Task3.POINT_RADIUS / 2, 70, 20);
        area.setBackground(new Color(0,0,0,0));
        area.setFont(new Font(area.getFont().getFontName(), area.getFont().getStyle(),area.getFont().getSize()-3));
        Ponto point = new Ponto(x,y,Xc,Yc,area);
        point.setVisible(isVisible);
        area.setVisible(isVisible);
        point.setBounds(Xc-Task3.POINT_RADIUS/2,Yc-Task3.POINT_RADIUS/2,Task3.POINT_RADIUS,Task3.POINT_RADIUS);
        if (!this.points.contains(point)) {
            this.points.add(point);
            this.add(point);
            this.add(area);
        }
        repaint();
    }


    public void setRadius(double R){
        this.kontur.setRadius(R);
        for(Ponto point:points){
            point.recalculation(this.kontur.R,this.scale,this.xCenter,this.yCenter);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        this.x = this.getX();
        this.y = this.getY();
        this.width = this.getWidth();
        this.height = this.getHeight();

        scale = ((xCenter>yCenter)?yCenter/20:xCenter/20);

        this.xCenter = x+width/2;
        this.yCenter = y+height/2;
        super.paintComponent(g);
        drawKontur(g);
        drawPoints();
        drawAxises(g);
    }

    void drawAxises(Graphics g){
        g.setColor(Task3.AXIS_COLOR);
        g.drawLine(x, yCenter, x+width,yCenter);
        g.drawLine(xCenter,y,xCenter,y+height);
    }
    void drawKontur(Graphics g){
        g.setColor(Task3.KONTUR_COLOR);
        Polygon pol = new Polygon();
        pol.addPoint(xCenter-(20*scale)/2, yCenter);
        pol.addPoint(xCenter,yCenter);
        pol.addPoint(xCenter,yCenter - 20*scale);
        pol.addPoint(xCenter + 20*scale, yCenter);
        pol.addPoint(xCenter,yCenter);
        pol.addPoint(xCenter,yCenter + 20*scale);
        pol.addPoint(xCenter-(20/2)*scale, yCenter+20*scale);
        g.fillPolygon(pol);
        g.fillArc(xCenter-20*scale,yCenter-20*scale, 20*2*scale,20*2*scale, 270, 90);
    }

    void drawPoints() {
        for (Ponto point : points) {
//            System.out.print((this.getComponents()[0].toString()));
//            System.out.print((this.getComponents()[0].isVisible()));
//            System.out.print((this.getComponents()[0].isValid()));
//            System.out.print((this.getComponents()[0].isDisplayable()));
//            System.out.print((this.getComponents()[0].isEnabled()));
//            System.out.println((this.getComponents()[0].isShowing()));
            if (kontur.isInKontur(point)) {
                if (point.animation!=null){point.animation.interrupt();}
                point.setVisible(true);
                point.setBlueColor();
                point.isInKontur = true;
                point.repaint();
            } else {
                if (point.isInKontur) {
                    point.isInKontur = false;
                    point.setRedColor();
                    point.animation = new Thread(new Animation(point, this.kontur.R,this.scale));
                    point.animation.start();
                } else {
                    point.setRedColor();
                    point.repaint();
                }
            }
        }
    }
    class Animation implements Runnable{
        Ponto point;
        double R;
        int scale;

        public Animation(Ponto point,double radius,int scale){
            this.point=point;
            this.R=radius;
            this.scale=scale;
        }

        @Override
        public void run() {
            while(!Thread.interrupted()) {
                resize(10);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    point.setRadius(Task3.POINT_RADIUS);
                    point.repaint();
                    return;
                }
                resize(15);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    point.setRadius(Task3.POINT_RADIUS);
                    point.repaint();
                    return;
                }
                resize(20);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    point.setRadius(Task3.POINT_RADIUS);
                    point.repaint();
                    return;
                }
            }
            point.setRadius(Task3.POINT_RADIUS);
            point.repaint();
        }
        void resize(double div){
            this.point.setRadius((int) (R / div * scale)+1);
            point.repaint();

        }
    }
}
