<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List,ru.vsu.cs.java.storehouse.models.Warehouse" %>
<%@ page import="ru.vsu.cs.java.storehouse.models.Warehouse" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список складов</title>
    <style>
        .layer {
            overflow: scroll; /* Добавляем полосы прокрутки */
            width: 300px; /* Ширина блока */
            height: 150px; /* Высота блока */
            padding: 5px; /* Поля вокруг текста */
            border: solid 1px black; /* Параметры рамки */
        }
    </style>
</head>
<body>
<hr>
<li><h4>Работа со складами:</h4><hr>
    <div class="layer">
<ul>
    <% for (Warehouse warehouses : (List<Warehouse>)request.getAttribute("ALL_WAREHOUSES")){ %>
    <li><%=warehouses.warehouseNumber%>
        <form action="/Web_app/warehouses" method="POST">
            <input type="hidden" name="action" value="remove"/>
            <input type="hidden" name="id" value="<%=warehouses.warehouseId%>"/>
            <input type="submit" value="Удалить"/>
        </form>
        <form action="/Web_app/warehouses/info" method="GET">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%=warehouses.warehouseId%>"/>
            <input type="submit" value="Подробнее..."/>
        </form>
    </li>
    <% } %>
</ul>
    </div>
    <hr>
<form action="/Web_app/warehouses" method="POST">
    <input type="hidden" name="action" value="create"/>
    <p>Номер склада</p>
    <input type="text" name="warehouseNumber"/>
    <p>Адрес</p>
    <input type="text" name="warehouseAddress"/>
    <p>Телефон</p>
    <input type="number" name="warehousePhone"/>
    <p>Почта</p>
    <input type="text" name="warehouseMail"/>
    <hr>
    <input type="submit" value="Создать склад"/>
    <hr>
</form>
</body>
</html>