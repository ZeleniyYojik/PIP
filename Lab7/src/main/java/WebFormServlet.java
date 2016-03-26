import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>ПИП Лаба №7</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"wrapper\">\n" +
                "    <header>\n" +
                "        <p>Пашнин Александр Денисович</p>\n" +
                "        <p>Группа P3217</p>\n" +
                "        <p>Вариант 1720</p>\n" +
                "    </header>\n" +
                "    <div class=\"content\">\n" +
                "        <div class=\"inputs\">\n" +
                "           <form method=\"POST\" action=\"/lab7/main\">\n" +
                "                <p>X value:\n" +
                "                <input type=\"text\" id=\"text\" name=\"XSelector\" placeholder=\"-3..5\" oninput=\"validateX(this)\" required>\n" +
                "                </p>\n" +
                "                <p>Y value:\n" +
                "                <input type=\"text\" id=\"text\" name=\"YSelector\" placeholder=\"-5..3\" oninput=\"validateY(this)\" required>\n" +
                "                </p>\n" +
                "                <p>R value:<select name=\"RSelector\">\n" +
                "                <option>1</option>\n" +
                "                <option>1.5</option>\n" +
                "                <option>2</option>\n" +
                "                <option>2.5</option>\n" +
                "                <option>3</option>\n" +
                "                </select></p>\n" +
                "                <button type=\"submit\" name=\"submit\">OK</button>\n" +
                "                </form>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n" +
                "<script>\n" +
                "    document.getElementById(\"text\").onkeypress= function(event){\n" +
                "        event= event || window.event;\n" +
                "        if (event.charCode && (event.charCode < 47 || event.charCode > 57)){" +
                "                if (event.charCode!=44  && event.charCode!=46 && event.charCode!=45 ) {\n" +
                "                return false;\n" +
                "                }\n" +
                "        }\n" +
                "    };\n" +
                "</script>\n" +
                "<style> \n" +
                "    .content{\n" +
                "        margin-top: 50px;\n" +
                "    }\n" +
                "    .inputs{\n" +
                "        width: 50%;\n" +
                "        float: left;\n" +
                "        padding-top: 50px;\n" +
                "    }\n" +
                "    .wrapper{\n" +
                "        margin: auto;\n" +
                "        width: 980px;\n" +
                "    }\n" +
                "    header>p{\n" +
                "        font-family: sans-serif;\n" +
                "        width: 300px;\n" +
                "        padding: 5px;\n" +
                "        text-align: center;\n" +
                "        margin: auto;\n" +
                "        color: #004dff;\n" +
                "    \n" +
                "}   \n" +
                "form{\n" +
                "        font-family: sans-serif;\n" +
                "    }\n" +
                "    header>p:first-child{\n" +
                "        font-size: 25px !important;\n" +
                "    }\n" +
                "    header>p:nth-child(2){\n" +
                "        font-size: 20px;\n" +
                "    }\n" +
                "    header>p:nth-child(3){\n" +
                "        font-size: 18px;\n" +
                "    }\n" +
                "    form>p>input,button,select{\n" +
                "        margin-left: 10px;\n" +
                "    }\n" +
                "    form>p>button{\n" +
                "        width: 30px;\n" +
                "        height: 30px;\n" +
                "    }\n" +
                "</style>");
        out.close();
    }
}
