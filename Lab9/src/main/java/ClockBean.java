import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "clock")
@ViewScoped
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
