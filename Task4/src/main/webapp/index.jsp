<%--
  Created by IntelliJ IDEA.
  User: Timey
  Date: 10.09.2018
  Time: 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>XMLParsing</title>
<link rel="stylesheet" href="stylesheet.css">
</head>
<body>
<h3>Welcome to Timey's magic XML parser!</h3>
<form action="XMLServlet" method="post" enctype="multipart/form-data">
<input required="required" type="file" name="inputFile">
<select name="parserType" title="Parser type">
    <option selected="selected" value="dom">DOM</option>
    <option value="sax">SAX</option>
    <option value="stax">StAX</option>
</select>
<input type="submit" value="Parse">
</form>
</body>
</html>
