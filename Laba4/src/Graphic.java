import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

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
        this.setLayout(null);
        this.setBackground(Task3.BACKGROUND_COLOR);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPoint(e.getX(), e.getY());
                System.out.print("Жмак!!");
            }
        });
        kontur = new Kontur(20);
    }

    public void addPoint(double x, double y){
        this.points.add(new Ponto(x,y));
    }

    public void setRadius(double R){
        this.kontur.setRadius(R);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.x = this.getX();
        this.y = this.getY();
        this.width = this.getWidth();
        this.height = this.getHeight();

        scale = (xCenter>yCenter?yCenter/20:xCenter/20);

        this.xCenter = x+width/2;
        this.yCenter =y+height/2;

        drawKontur(g);
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
        pol.addPoint(xCenter-(int)this.kontur.R/2*scale, yCenter);
        pol.addPoint(xCenter,yCenter);
        pol.addPoint(xCenter,yCenter - (int)this.kontur.R*scale);
        pol.addPoint(xCenter + (int)this.kontur.R*scale, yCenter);
        pol.addPoint(xCenter,yCenter);
        pol.addPoint(xCenter,yCenter + (int)this.kontur.R*scale);
        pol.addPoint(xCenter-(int)this.kontur.R/2*scale, yCenter+(int)this.kontur.R*scale);
        g.fillPolygon(pol);
        g.fillArc(xCenter-(int)this.kontur.R*scale,yCenter-(int)this.kontur.R*scale,
                (int)this.kontur.R*scale*2,(int)this.kontur.R*scale*2, 270, 90);
    }

}
