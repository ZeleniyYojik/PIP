import javax.swing.*;
import java.awt.*;


/**
 * Created by panikun on 26.10.15.
 */

class Window extends JFrame{
    Window(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setSize(1024, 768);
        this.setMinimumSize(new Dimension(240, 240));
        this.setResizable(false);
        Graphic graphic = new Graphic();
        this.add(graphic);
        Tools toolsPanel = new Tools(graphic);
        this.add(toolsPanel, BorderLayout.SOUTH);

        //super.add(new RightPanel(leftPanel));

        this.setVisible(true);
    }
}

/*class Graphic extends JPanel{
    Set<Ponto> points;
    Kontur kontur;

    Graphic(double radius){
        points = new HashSet<Ponto>();
        kontur = new Kontur(radius);
    }
    public void addPoint(Ponto point){
        points.add(point);
    }
    public void addPoint(double X, double Y){
        points.add(new Ponto(X,Y));
    }

    public void painting(JPanel canvas){
    }
}*/
