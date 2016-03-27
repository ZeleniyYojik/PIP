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
                "           <form method=\"POST\" id=\"forma\" action=\"/lab7/main\">\n" +
                "                <p>X value {-3..5}:\n" +
                "                <input type=\"text\" id=\"textX\" name=\"XSelector\" placeholder=\"-3..5\" onchange=\"validateForm()\" onkeydown=\"javascript:if(13==event.keyCode){return false;}\" required>\n" +
                "                </p>\n" +
                "                <p>Y value {-5..3}:\n" +
                "                <input type=\"text\" id=\"textY\" name=\"YSelector\" placeholder=\"-5..3\" onchange=\"validateForm()\" onkeydown=\"javascript:if(13==event.keyCode){return false;}\" required>\n" +
                "                </p>\n" +
                "                <p>R value:<select name=\"RSelector\">\n" +
                "                <option>1</option>\n" +
                "                <option>1.5</option>\n" +
                "                <option>2</option>\n" +
                "                <option>2.5</option>\n" +
                "                <option>3</option>\n" +
                "                </select></p>\n" +
                "                <button type=\"submit\" id=\"ok\" name=\"submit\">OK</button>\n" +
                "                </form>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n" +
                "<script>\n" +
                "    function validateForm(){\n" +
                "        var x_val=document.getElementById(\"textX\").value;\n" +
                "        var y_val=document.getElementById(\"textY\").value;\n" +
                "        x_val=x_val.replace(\",\",\".\");\n" +
                "        y_val=y_val.replace(\",\",\".\");\n" +
                "        var x_valid=!((x_val == \"\") || !(!isNaN(parseFloat(x_val)) && isFinite(x_val)) || (x_val > 5) || (x_val < -3));\n" +
                "        var y_valid=!((y_val == \"\") || !(!isNaN(parseFloat(y_val)) && isFinite(y_val)) || (y_val > 3) || (y_val < -5));\n" +
                "        if (x_valid&&y_valid) {\n" +
                "            document.getElementById(\"ok\").disabled=false;\n" +
                "         }else{\n" +
                "            document.getElementById(\"ok\").disabled=true;\n" +
                "         }\n" +
                "    }\n" +
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
                "    header>p:nth-child(3){  \n" +
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
