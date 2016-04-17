import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "clock")
public class ClockBean {
    private String mode;

    public ClockBean() {
    }

    @PostConstruct
    public void init() {
        mode = "client";
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
