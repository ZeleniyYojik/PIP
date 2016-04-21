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

    public Object getResults() {
        return results;
    }

}
