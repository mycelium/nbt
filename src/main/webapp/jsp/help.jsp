<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<c:url value="/site" var="api_url" />

<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/jquery-ui.css"/>" />
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/style.css"/>"/>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>

<title>FAQ</title>
</head>
<body>

    <div class="container content">
        <div class="container-fluid">
            <div class="row-fluid">
			<c:import url="/jsp/header.jsp" />
                <div class="span5">
                    <h3>Feedback:</h3><br>
                            <h5>What`s a problem? </h5>
							<input id="question" type="text" class="input-xlarge" placeholder="Your question">
                            <textarea rows="3" input id="showMeArea"  type="text" class="input-xlarge">
                            </textarea>
                            <input id="showMessage" type="button" class="btn btn-info input-xlarge" value="Show Message!">
                </div>
                <script>
                    $('#showMessage').on('click', function(){
                    $('#showMeArea').val('The answer to Ur question <'+question.value+ '> will be sent by email!');});
                </script>
            </div>
        </div>
    </div>

</body>
</html>