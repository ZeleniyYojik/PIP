import tools.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);
        List<Result> res = (ArrayList<Result>) session.getAttribute("results");

        String XBuf = req.getParameter("XSelector").replace(',', '.');
        String YBuf = req.getParameter("YSelector").replace(',', '.');
        String RBuf = req.getParameter("RSelector").replace(',', '.');
        double X = Double.NaN;
        double Y = Double.NaN;
        double R = Double.NaN;
        try {
            X = Double.parseDouble(XBuf);
            Y = Double.parseDouble(YBuf);
            R = Double.parseDouble(RBuf);
        } catch (NumberFormatException e) {

        }
        if (!validate(X, Y, R)) {
            return;
        }
        if (res == null) {
            res = new ArrayList<Result>();
            res.add(new Result(X, Y, R, inFigure(X, Y, R)));
            session.setAttribute("results", res);
        } else {
            res.add(new Result(X, Y, R, inFigure(X, Y, R)));
        }
        resp.sendRedirect("/lab8/view.jsp");
    }

    boolean inFigure(double X, double Y, double R) {
        return ((Y >= 0 && X >= 0 && Y <= R - X)
                || (Y >= 0 && X <= 0 && (R / 2) * (R / 2) >= X * X + Y * Y)
                || (Y <= 0 && Y >= -R && X <= 0 && X >= -R));
    }

    boolean validate(double X, double Y, double R) {
        return (!Double.isNaN(X) && !Double.isNaN(Y) && !Double.isNaN(R) && R > 0 && Y <= 5 && Y >= -3);
    }
}
