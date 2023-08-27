<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List,ru.vsu.cs.java.storehouse.models.Product" %>
<%@ page import="ru.vsu.cs.java.storehouse.models.Product" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список продуктов</title>
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
<li><h4>Работа с продуктами:</h4><hr>
<div class="layer">
    <ul>
        <% for (Product product : (List<Product>)request.getAttribute("ALL_PRODUCTS")){ %>
        <li><%=product.title%>
            <form action="/Web_app/products" method="POST">
                <input type="hidden" name="action" value="remove"/>
                <input type="hidden" name="id" value="<%=product.productId%>"/>
                <input type="submit" value="Удалить"/>
            </form>
            <form action="/Web_app/products/info" method="GET">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<%=product.productId%>"/>
                <input type="submit" value="Подробнее..."/>
            </form>
        </li>
        <% } %>
    </ul>
</div>
<hr>
<form action="/Web_app/products" method="POST">
    <input type="hidden" name="action" value="create"/>
    <p>Название</p>
    <input type="text" name="title"/>
    <p>Цена</p>
    <input type="number" name="price"/>
    <p>Количество</p>
    <input type="number" name="quantity"/>
    <p>Дата доставки</p>
    <input type="text" name="dateOfDelivery"/>
    <p>Описание</p>
    <input type="text" name="description"/>
    <p>Производитель</p>
    <input type="text" name="manufacturer"/>
    <hr>
    <input type="submit" value="Создать продукт"/>
    <hr>
</form>
</body>
</html>