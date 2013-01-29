<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/style.css"/>"/>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <title>NBT</title>
</head>
<c:url value="/site" var="api_url"/>

<body>
    <div class="container content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2">
                    <ul class="nav nav-list">
                        <li class="nav-header">List header</li>
                        <li class="active"><a href="/nbt">Home</a></li>
                        <li><a href="${api_url}/hello.jsp">Hello page</a></li>
                        <li><a href="${api_url}/template.jsp">Show template</a></li>
                        <li><a href="${api_url}/new_issue.jsp">New Issue</a> </li>
                        <li class="nav-header">Another list header</li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                </div>

    <div class="span10">
        <div class="btn-group">
        <button class="btn btn-small dropdown-toggle btn-success" data-toggle="dropdown">Large button <span class="caret"></span></button>
        <ul class="dropdown-menu">
        <li><a href="#">Action</a></li>
        <li><a href="${api_url}/hello">Hello page</a></li>
        <li><a href="#">Something else here</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
    </ul>

    </div>
    </div>
    </div>
    </div>
    </div>
</body>
</html>
