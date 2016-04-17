import javax.faces.bean.ManagedBean;

@ManagedBean(name = "check")
public class CheckBean {
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

    public CheckBean() {
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
        //Пересчитваем значения существующих точек для текущего радиуса
        //Понадобится для динамической картинки, возможно. Пока не ясно как она должна выглядеть
//        for (Point p : ResultsBean.getRes()) {
//            p.setRadius(R);
//            p.setInArea(inArea(p.getxCoord(), p.getyCoord(), R));
//        }
        //Самое стремное
        //Надо проверить, есть ли такая точка уже в результатах
        //Довольно затратно
        if (isXm3()) {
            double x = -3;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
        if (isXm2()) {
            double x = -2;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
        if (isXm1()) {
            double x = -1;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
        if (isX0()) {
            double x = 0;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
        if (isX3()) {
            double x = 3;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
        if (isX2()) {
            double x = 2;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
        if (isX1()) {
            double x = 1;
            Point point = new Point(x, y, r, inArea(x, y, r));
            if (!ResultsBean.contains(point)) {
                ResultsBean.addResult(point);
            }
        }
    }

    private boolean inArea(double x, double y, double r) {
        return (y <= r && r >= 0 && x >= 0 && x <= r && r * r >= x * x + y * y)
                || (y <= 0 && x >= 0 && x <= r && y >= (x - r) / 2)
                || (y <= 0 && y >= -r && x <= 0 && x >= -r / 2);
    }
}
