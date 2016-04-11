import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("YSelector") == null || req.getParameter("XSelector") == null || req.getParameter("RSelector") == null) {
            req.getRequestDispatcher("/view.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("check").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view.jsp").forward(req, resp);
    }
}
