import javax.faces.bean.ManagedBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
public class Clock {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String date = dateFormat.format(new Date());

    public String getDate() {
        return date;
    }
}
