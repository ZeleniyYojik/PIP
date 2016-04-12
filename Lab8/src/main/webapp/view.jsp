<%@ page import="java.util.ArrayList" %>
<%@ page import="tools.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="style.css" %>
</style>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <title>ПИП Лаба №8</title>
</head>
<body>
<script>
    <%@include file="scripts.js"%>
</script>
<div class="wrapper">
    <header>
        <p>Пашнин Александр Денисович</p>
        <p>Группа P3217</p>
        <p>Вариант 1727</p>
    </header>
    <div class="content">
        <div class="inputs">
            <form method="GET" action="/lab8/main">
                <p>X value:
                    <input type="checkbox" name="XSelector" class="chb" value="-2" checked>-2
                    <input type="checkbox" name="XSelector" class="chb" value="-1.5">-1.5
                    <input type="checkbox" name="XSelector" class="chb" value="-1">-1
                    <input type="checkbox" name="XSelector" class="chb" value="-0.5">-0.5
                    <input type="checkbox" name="XSelector" class="chb" value="0">0
                    <input type="checkbox" name="XSelector" class="chb" value="0.5">0.5
                    <input type="checkbox" name="XSelector" class="chb" value="1">1
                    <input type="checkbox" name="XSelector" class="chb" value="1.5">1.5
                    <input type="checkbox" name="XSelector" class="chb" value="2">2
                <p>Y value {-3..5}:
                    <input type="text" id="textY" name="YSelector" placeholder="-3..5" onchange="validateForm()"
                           onkeydown="javascript:if(13==event.keyCode){return false;}" required>
                </p>
                <p id="rad">Select R:
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="1">1</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="2">2</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="3">3</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="4">4</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="5">5</button>
                </p>
                <input type="hidden" id="hiddenR" name="RSelector"></input>
                <button type="submit" id="ok" disabled="true">OK</button>
            </form>
        </div>
        <div class="area">
            <p><img name='img' src='http://i.imgur.com/NYTtEHF.png' id="graph" style="position: relative;"/></p>
        </div>
        <table border="1" style="border-color: lightsteelblue">
            <caption>Result table</caption>
            <tr>
                <td>X</td>
                <td>Y</td>
                <td>R</td>
                <td>Contain</td>
            </tr>
            <%
                ArrayList<Result> res = (ArrayList) session.getAttribute("results");
                if (res != null) {
                    for (int i = 0; i < res.size(); i++) {
            %>
            <tr>
                <td>
                    <%= res.get(i).x %>
                </td>
                <td>
                    <%= res.get(i).y %>
                </td>
                <td>
                    <%= res.get(i).r %>
                </td>
                <td>
                    <%= res.get(i).inArea %>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</div>
</body>
</html>