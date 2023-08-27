<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List,ru.vsu.cs.java.storehouse.models.Shipper" %>
<%@ page import="ru.vsu.cs.java.storehouse.models.Shipper" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список поставщиков</title>
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
<li><h4>Работа с поставщиками:</h4><hr>
    <div class="layer">
    <ul>
        <% for (Shipper shippers : (List<Shipper>)request.getAttribute("ALL_SHIPPERS")){ %>
        <li><%=shippers.shipperName%>
            <form action="/Web_app/shippers" method="POST">
                <input type="hidden" name="action" value="remove"/>
                <input type="hidden" name="id" value="<%=shippers.shipperId%>"/>
                <input type="submit" value="Удалить"/>
            </form>
            <form action="/Web_app/shippers/info" method="GET">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<%=shippers.shipperId%>"/>
                <input type="submit" value="Подробнее..."/>
            </form>
        </li>
        <% } %>
    </ul>
        </div>
    <hr>
<form action="/Web_app/shippers" method="POST">
    <input type="hidden" name="action" value="create"/>
    <p>Наименование поставщика</p>
    <input type="text" name="shipperName"/>
    <p>Адрес</p>
    <input type="text" name="shipperAddress"/>
    <p>Номер телефона</p>
    <input type="number" name="phone"/>
    <p>Почта</p>
    <input type="text" name="mail"/>
    <hr>
    <input type="submit" value="Создать поставщика"/>
    <hr>
</form>
</body>
</html>