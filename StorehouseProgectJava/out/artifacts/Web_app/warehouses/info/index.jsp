<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List,ru.vsu.cs.java.storehouse.models.Warehouse" %>
<%@ page import="ru.vsu.cs.java.storehouse.models.Warehouse" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список Складов</title>
</head>
<body>
<ul><hr>
    <% Warehouse warehouses = (Warehouse) request.getAttribute("ALL_WAREHOUSES"); %>
    <li><h4>Подробная информация о поставщике:</h4><hr>
        <b>Номер склада: </b><%=warehouses.warehouseNumber%><br>
        <b>Адрес: </b><%=warehouses.warehouseAddress%><br>
        <b>Номер телефона: </b><%=warehouses.warehousePhone%><br>
        <b>Электронная почта: </b><%=warehouses.warehouseMail%><br>
        <hr>
        <form action="/Web_app/warehouses/info" method="POST">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%=warehouses.warehouseId%>"/>
            <p>Номер склада</p>
            <input type="text" name="warehouseNumber" value="<%=warehouses.warehouseNumber%>"/>
            <p>Адрес</p>
            <input type="text" name="warehouseAddress" value="<%=warehouses.warehouseAddress%>"/>
            <p>Номер телефона</p>
            <input type="number" name="warehousePhone" value="<%=warehouses.warehousePhone%>"/>
            <p>Почта</p>
            <input type="text" name="warehouseMail" value="<%=warehouses.warehouseMail%>"/>
            <hr>
            <input type="submit" value="изменить"/>
            <hr>
        </form>
    </li>
</ul>
</body>
</html>