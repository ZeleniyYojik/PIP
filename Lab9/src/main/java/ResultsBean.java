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
        results.add(new Point(1,2,3,true));
        results.add(new Point(0,2,3,false));
        results.add(new Point(2,2,3,true));
        results.add(new Point(3,2,3,false));
    }

    public Object getResults() {
        return results;
    }
}
