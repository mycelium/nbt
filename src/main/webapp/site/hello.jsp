<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<c:url value="/site" var="api_url"/>

<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/jquery-ui.css"/>" />
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/style.css"/>"/>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>

    <title>NBT Greets U</title>
</head>
<body>

    <div class="container content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2">
                    <ul class="nav nav-list">
                        <li class="nav-header">List header</li>
                        <li><a href="/nbt">Home</a></li>
                        <li class="active"><a href="${api_url}/hello.jsp">Hello page</a></li>
                        <li><a href="${api_url}/template.jsp">Show template</a></li>
                        <li><a href="${api_url}/new_issue.jsp">New Issue</a> </li>
                        <li class="nav-header">Another list header</li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                </div>

                <div class="span8">
                    <div class="btn-group">
                        <h2>Hello service</h2>
                        <tb>
                            <input id="inputName" type="text"  placeholder="Type your name">
                            <input id="sumbitButton" type="button" class="btn btn-success" value="submit" onclick="showHelloDialog()">
                        </tb>
                    </div>
                </div>
            </div>
        </div>
    </div>



<script type="text/javascript">

    var xmlhttp = new XMLHttpRequest();


    function showHelloDialog() {
        var name=$("#inputName").val();
        if(!name){
            alert('Empty name!');
            return;
        }
        $.getJSON(window.location.href+"/greeting/"+name,{},
        function (data){
        if(data.greeting){
            $("#dialogHello").append("<p>"+data.greeting+"</p>");
        }else{
            alert('No data returned!');
        }
        });
        $("#dialogHello").dialog({modal:true, width:300, height:150,buttons: {
            "Ok": function() {
            $(this).dialog( "close" );
            }
        }});
    }

</script>
</body>
</html>