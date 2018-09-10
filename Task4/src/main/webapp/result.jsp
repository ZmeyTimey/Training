<%--
  Created by IntelliJ IDEA.
  User: Timey
  Date: 10.09.2018
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>XMLParsing</title>
    <link rel="stylesheet" href="stylesheet.css">
</head>
<body>
<jsp:useBean id="flowers" scope="request" type="java.util.Set"/>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Family</th>
        <th>Is evergreen</th>
        <th>Soil</th>
        <th>Origin</th>
        <th>Delivery date</th>
        <th>Stalk color</th>
        <th>Leaves color</th>
        <th>Average size</th>
        <th>Min temperature</th>
        <th>Max temperature</th>
        <th>Is photophilous</th>
        <th>Watering</th>
        <th>Propagation</th>
    </tr>
    <c:forEach items="${flowers}" var="flower">
        <tr>
            <td><c:out value="${flower.id}"/></td>
            <td><c:out value="${flower.name}"/></td>
            <td><c:out value="${flower.family}"/></td>
            <td><c:out value="${flower.isEvergreen}"/></td>
            <td><c:out value="${flower.deliveryDate}"/></td>
            <td><c:out value="${flower.visualParameters.stalkColor}"/></td>
            <td><c:out value="${flower.visualParameters.leavesColor}"/></td>
            <td><c:out value="${flower.visualParameters.averageSize}"/></td>
            <td><c:out value="${flower.growingTips.temperature.min}"/></td>
            <td><c:out value="${flower.growingTips.temperature.max}"/></td>
            <td><c:out value="${flower.growingTips.isPhotophilous}"/></td>
            <td><c:out value="${flower.growingTips.watering}"/></td>
            <td><c:out value="${flower.propagation}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
