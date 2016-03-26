<html>
<head>
    <title>Lab7</title>
</head>
<body>
<h2>JSP</h2>
<form method=POST action=acer:8080/lab7/ControllerServlet>
    <p>X value:
        <input type=text name=XSelector placeholder=-3..5 required>
    </p>
    <p>Y value:
        <input type=text name=YSelector placeholder=-5..3 required>
    </p>
    <p>R value:
        <select name=RSelector>
            <option>1</option>

            <option>1.5</option>

            <option>2</option>

            <option>2.5</option>

            <option>3</option>

        </select>
    </p>
    <button type=submit name=submit>OK</button>
</form>
</body>
</html>
