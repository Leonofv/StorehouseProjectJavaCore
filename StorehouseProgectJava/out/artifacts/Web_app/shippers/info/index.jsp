<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List,ru.vsu.cs.java.storehouse.models.Shipper" %>
<%@ page import="ru.vsu.cs.java.storehouse.models.Shipper" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список Поставщиков</title>
</head>
<body>
<ul><hr>
    <% Shipper shippers = (Shipper) request.getAttribute("ALL_SHIPPERS"); %>
    <li><h4>Подробная информация о поставщике:</h4><hr>
        <b>Наименование поставщика: </b><%=shippers.shipperName%><br>
        <b>Адрес: </b><%=shippers.shipperAddress%><br>
        <b>Номер телефона: </b><%=shippers.phone%><br>
        <b>Электронная почта: </b><%=shippers.mail%><br>
        <hr>
        <form action="/Web_app/shippers/info" method="POST">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%=shippers.shipperId%>"/>
            <p>Наименование поставщика</p>
            <input type="text" name="shipperName" value="<%=shippers.shipperName%>"/>
            <p>Адрес</p>
            <input type="text" name="shipperAddress" value="<%=shippers.shipperAddress%>"/>
            <p>Номер телефона</p>
            <input type="number" name="phone" value="<%=shippers.phone%>"/>
            <p>Почта</p>
            <input type="text" name="mail" value="<%=shippers.mail%>"/>
            <hr>
            <input type="submit" value="изменить"/>
            <hr>
        </form>
    </li>
</ul>
</body>
</html>