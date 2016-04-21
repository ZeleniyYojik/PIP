import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "check")
@ViewScoped
public class CheckBean implements Serializable {
    /*Возможно, лучше было бы использовать
      selectManyCheckboxes
     */
    private boolean Xm3;
    private boolean Xm2;
    private boolean Xm1;
    private boolean X0;
    private boolean X1;
    private boolean X2;
    private boolean X3;
    private String Y;
    private double R;
    private String yClick;
    private String xClick;

    @ManagedProperty(value = "#{imageBean}")
    private ImageBean imageBean;


    public CheckBean() {
        R = 1;
    }

    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    public String getyClick() {
        return yClick;
    }

    public void setyClick(String yClick) {
        this.yClick = yClick;
    }

    public String getxClick() {
        return xClick;
    }

    public void setxClick(String xClick) {
        this.xClick = xClick;
    }

    public boolean isXm3() {
        return Xm3;
    }

    public void setXm3(boolean xm3) {
        Xm3 = xm3;
    }

    public boolean isXm2() {
        return Xm2;
    }

    public void setXm2(boolean xm2) {
        Xm2 = xm2;
    }

    public boolean isXm1() {
        return Xm1;
    }

    public void setXm1(boolean xm1) {
        Xm1 = xm1;
    }

    public boolean isX0() {
        return X0;
    }

    public void setX0(boolean x0) {
        X0 = x0;
    }

    public boolean isX1() {
        return X1;
    }

    public void setX1(boolean x1) {
        X1 = x1;
    }

    public boolean isX2() {
        return X2;
    }

    public void setX2(boolean x2) {
        X2 = x2;
    }

    public boolean isX3() {
        return X3;
    }

    public void setX3(boolean x3) {
        X3 = x3;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public void checkPoint() {
        double y = Double.parseDouble(Y.replace(',', '.'));
        double r = R;
        if (isXm3()) {
            double x = -3;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        if (isXm2()) {
            double x = -2;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        if (isXm1()) {
            double x = -1;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        if (isX0()) {
            double x = 0;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        if (isX3()) {
            double x = 3;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        if (isX2()) {
            double x = 2;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        if (isX1()) {
            double x = 1;
            Point point = new Point(x, y, r, inArea(x, y, r));
            ResultsBean.addResult(point);
            imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        }
        imageBean.setRadius(r);
        checkExistedPoints(r);
    }

    public void checkClick() {
        double y = Double.parseDouble(yClick.replace(',', '.'));
        double x = Double.parseDouble(xClick.replace(',', '.'));
        y = Math.rint(100.0 * y) / 100.0;
        x = Math.rint(100.0 * x) / 100.0;
        double r = R;
        Point point = new Point(x, y, r, inArea(x, y, r));
        ResultsBean.addResult(point);
        imageBean.addPoint(new Point(point.getxCoord(), point.getyCoord(), point.getRadius(), point.isInArea()));
        imageBean.setRadius(r);
        checkExistedPoints(r);
    }

    private boolean inArea(double x, double y, double r) {
        return (y <= r && y >= 0 && x >= 0 && x <= r && r * r >= x * x + y * y)
                || (y <= 0 && x >= 0 && x <= r && y >= x / 2 - r / 2)
                || (y <= 0 && y >= -r && x <= 0 && x >= -r / 2);
    }

    private void checkExistedPoints(double r) {
        for (Point p : imageBean.getPoints()) {
            p.setRadius(r);
            p.setInArea(inArea(p.getxCoord(), p.getyCoord(), r));
        }
    }

    public void changedRadius() {
        imageBean.setRadius(R);
        checkExistedPoints(R);
    }
}
