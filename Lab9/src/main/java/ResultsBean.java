import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "results")
@ApplicationScoped
public class ResultsBean implements Serializable {
    private static List<Point> results = new ArrayList<Point>();

    public ResultsBean() {
    }

    public static void addResult(Point p) {
        results.add(p);
    }

    public static boolean contains(Point p) {
        for (Point point : results) {
            if (p.xCoord == point.xCoord && p.yCoord == point.yCoord && p.radius == point.radius) {
                return true;
            }
        }
        return false;
    }

    public Object getResults() {
        return results;
    }

    public static List<Point> getRes(){
        return results;
    }
}
