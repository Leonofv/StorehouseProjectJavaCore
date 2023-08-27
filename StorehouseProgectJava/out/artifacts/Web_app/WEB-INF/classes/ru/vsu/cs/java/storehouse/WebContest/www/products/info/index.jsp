<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List,ru.vsu.cs.java.storehouse.models.Product" %>
<%@ page import="ru.vsu.cs.java.storehouse.models.Product" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список продуктов</title>
</head>
<body>
<ul><hr>
<% Product products = (Product) request.getAttribute("ALL_PRODUCTS"); %>
<li><h4>Подробная информация о товаре:</h4><hr>
<b>Название товара: </b><%=products.title%><br>
<b>Id товара: </b><%=products.productId%><br>
<b>Id склада: </b><%=products.warehouseId%><br>
<b>Цена: </b><%=products.price%><br>
<b>Количество: </b><%=products.quantity%><br>
<b>Дата доставки: </b><%=products.dateOfDelivery%><br>
<b>Описание товара: </b><%=products.description%><br>
<b>Производитель: </b><%=products.manufacturer%><br>
<hr>
<form action="/Web_app/products/info" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="<%=products.productId%>"/>
    <p>Название:</p>
    <input type="text" name="title" value="<%=products.title%>"/>
    <%--<p>Номер склада:</p>--%>
    <input type="hidden" name="warehouse_id" value="<%=products.warehouseId%>"/>
    <p>Цена:</p>
    <input type="number" name="price" value="<%=products.price%>"/>
    <p>Количество:</p>
    <input type="number" name="quantity" value="<%=products.quantity%>"/>
    <p>Дата доставки:</p>
    <input type="text" name="dateOfDelivery" value="<%=products.dateOfDelivery%>"/>
    <p>Описание:</p>
    <input type="text" name="description" value="<%=products.description%>"/>
    <p>Производитель:</p>
    <input type="text" name="manufacturer" value="<%=products.manufacturer%>"/>
    <hr>
    <input type="submit" value="изменить"/>
    <hr>
</form>
</li>
    </ul>
</body>
</html>