import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String XBuf = req.getParameter("XSelector").replace(',', '.');
        String YBuf = req.getParameter("YSelector").replace(',', '.');
        String RBuf = req.getParameter("RSelector").replace(',', '.');
        float X = Float.NaN;
        float Y = Float.NaN;
        float R = Float.NaN;
        try {
            X = Float.parseFloat(XBuf);
            Y = Float.parseFloat(YBuf);
            R = Float.parseFloat(RBuf);
        } catch (NumberFormatException e) {
        }
        PrintWriter out = resp.getWriter();
        if (!validate(X,Y,R)){
            out.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>ПИП Лаба №7</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <p>Неверные данные</p>\n" +
                    "    <p>X должен быть числом {-3..5}. X="+X+"</p>\n" +
                    "    <p>Y должен быть числом {-5..3}. Y="+Y+"</p>\n" +
                    "    <p>R должен быть положительным числом. R="+R+"</p>\n" +
                    "</body>\n" +
                    "</html>");
            out.close();
            return;
        }
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Lab7</title>");
        out.println("<meta charset=\"utf-8\">");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        out.println("</body>");
        out.println("</html>");
    }

    boolean inFigure(float X, float Y, float R) {
        return (Y >= 0 && Y <= R && X <= 0 && X >= R / 2) &&
                (Y >= -R / 2 && Y <= 0 && X >= -R / 2 && X <= 0 && (X * X + Y * Y) >= R * R) &&
                (Y >= -R / 2 && Y <= 0 && X >= 0 / 2 && X <= R && Y >= X / 2 - R / 2);
    }

    boolean validate(float X, float Y, float R) {
        return (!Float.isNaN(X) && !Float.isNaN(Y) && !Float.isNaN(R) && R > 0 && X <= 5 && X >= -3 && Y <= 3 && Y >= -5);
    }
}
