<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/style.css"/>"/>    
    <title>NBT</title>
</head>
<c:url value="/site" var="api_url"/>
<body>
<h2>Hello World!</h2>
<a href="${api_url}/hello">Hello page</a>
</body>
</html>
