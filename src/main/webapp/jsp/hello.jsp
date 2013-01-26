<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<c:url value="/hello" var="api_url"/>

<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
	<link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/jquery-ui.css"/>" />
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/style.css"/>"/>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

    <title>Hello page</title>
</head>
<body>
<h2>Hello service</h2>
	<input id="inputName" type="text"  placeholder="Type your name">
	<input id="buttonSubmitName" type="button" value="submit" onclick="showHelloDialog()">
	<input id="buttonSubmitTitle" type="button" value = "change title" onclick="showSecondDialog" >

<div id="dialogHello" style="display:none" title="Hello!" >
	
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

    function showSecondDialog() {
          alert('go to hell');
    }

</script>
</body>
</html>