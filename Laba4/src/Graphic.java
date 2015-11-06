import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

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
    int ris = 0;
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
        double Xc = (double)(x-xCenter)/(double)scale;
        double Yc = (double)(yCenter-y)/(double)scale;
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
        int Xc = xCenter+(int)(x*scale);
        int Yc = yCenter-(int)(y*scale);
        JTextArea area = new JTextArea();
        area.setBounds(Xc - Task3.POINT_RADIUS / 2, Yc - Task3.POINT_RADIUS / 2, 70, 20);
        area.setBackground(new Color(0,0,0,0));
        area.setFont(new Font(area.getFont().getFontName(), area.getFont().getStyle(),area.getFont().getSize()-3));
        Ponto point = new Ponto(x,y,Xc,Yc,area);
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
        pol.addPoint(xCenter-(int)(this.kontur.R*scale)/2, yCenter);
        pol.addPoint(xCenter,yCenter);
        pol.addPoint(xCenter,yCenter - (int)this.kontur.R*scale);
        pol.addPoint(xCenter + (int)this.kontur.R*scale, yCenter);
        pol.addPoint(xCenter,yCenter);
        pol.addPoint(xCenter,yCenter + (int)this.kontur.R*scale);
        pol.addPoint(xCenter-(int)(this.kontur.R*scale)/2, yCenter+(int)this.kontur.R*scale);
        g.fillPolygon(pol);
        g.fillArc(xCenter-(int)this.kontur.R*scale,yCenter-(int)this.kontur.R*scale,
                (int)this.kontur.R*scale*2,(int)this.kontur.R*scale*2, 270, 90);
    }

    void drawPoints() {

        for (Ponto point : points) {
/*            int realX = xCenter + (int) (point.X * scale);
            int realY = yCenter - (int) (point.Y * scale);
            point.setNewRealCoords(realX, realY);*/
            if (kontur.isInKontur(point)) {
                if (point.animation!=null){point.animation.interrupt();}
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
        Graphic canvas;
        double R;
        int scale;
        //Ponto anim;


        public Animation(Ponto point/*Graphic canvas*/,double radius,int scale){
            //this.canvas=canvas;
            this.point=point;
            this.R=radius;
            this.scale=scale;
        }

        @Override
        public void run() {

            Rectangle bounds = this.point.getBounds();
           // anim = new Ponto(point.X,point.Y,point.realX,point.realY,point.textArea);
           // anim.setRedColor();
            //canvas.add(anim);
            while(!Thread.interrupted()) {
                /*this.point.setRadius((int) (canvas.kontur.R / 10 * canvas.scale));
                this.point.setBounds((int) bounds.getX() - point.radius / 2, (int) bounds.getY() - point.radius / 2, point.radius, point.radius);
                point.repaint();
                sleepThr();
                this.point.setRadius((int) (canvas.kontur.R / 15 * canvas.scale));
                this.point.setBounds((int) bounds.getX() - point.radius / 2, (int) bounds.getY() - point.radius / 2, point.radius, point.radius);
                point.repaint();
                sleepThr();
                this.point.setRadius((int) (canvas.kontur.R / 20 * canvas.scale));
                this.point.setBounds((int) bounds.getX() - point.radius / 2, (int) bounds.getY() - point.radius / 2, point.radius, point.radius);
                point.repaint();
                sleepThr();
                this.point.setRadius((int) (canvas.kontur.R / 15 * canvas.scale));
                this.point.setBounds((int) bounds.getX() - point.radius / 2, (int) bounds.getY() - point.radius / 2, point.radius, point.radius);
                point.repaint();
                sleepThr();*/
                resize(bounds,10);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    point.setBounds(bounds);
                    point.setRadius(Task3.POINT_RADIUS);
                    point.repaint();
                    return;
                }
                resize(bounds,15);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    point.setBounds(bounds);
                    point.setRadius(Task3.POINT_RADIUS);
                    point.repaint();
                    return;
                }
                resize(bounds,20);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    point.setBounds(bounds);
                    point.setRadius(Task3.POINT_RADIUS);
                    point.repaint();
                    return;
                }
                resize(bounds,15);
/*                this.point.setRadius((int) (canvas.kontur.R / 10 * canvas.scale));
                this.point.setBounds((int) bounds.getX() - point.radius / 2, (int) bounds.getY() - point.radius / 2, point.radius, point.radius);
                point.repaint();*/
                //sleepThr();
            }
            point.setBounds(bounds);
            point.setRadius(Task3.POINT_RADIUS);
            point.repaint();
            //canvas.remove(point);
           /* point.setBounds(bounds);
            point.setRadius(Task3.POINT_RADIUS);
            point.repaint();*/
        }
        void resize(Rectangle bounds, double div){
            this.point.setRadius((int) (/*canvas.kontur.*/R / div * /*canvas.*/scale));
            this.point.setBounds((int) bounds.getX() - point.radius / 2, (int) bounds.getY() - point.radius / 2, point.radius, point.radius);
            point.repaint();

        }
    }
}
