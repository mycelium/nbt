
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<c:url value="/site/developer" var="api_url" />

<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/js/bootstrap-datepicker.js"/>"></script>

<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/datepicker.css"/>" />

<title>Module view</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<form id="editModule" name="editModule" action="${api_url}/editModule" method="POST">
				<div class="span10">
				
					<h3>Module View</h3>
				</div><br>
				<div class="span5">
				<input id="moduleId" name="moduleId" type="hidden" value="${moduleView.id}">
					<h5>Title</h5>
					<input id="moduleCaption" name="moduleCaption" type="text" placeholder="Title" value="${moduleView.caption}" required>	
					<br>
					<h5>Description</h5>
					<textarea rows="7"  id="moduleDescription" name="moduleDescription" placeholder="Description"> ${moduleView.description}</textarea>
					<h5>Attached Tasks:</h5>
					<select id="taskId" name="taskId" multiple>
					<c:forEach items="${moduleView.attachedTasks}" var="task">
						<option>${task}</option>
					</c:forEach>
					</select>
					
				</div>
				<div class="span5">
					<h5>Date of creation</h5>
					<input type="text" id="moduleDateOfStart" name="moduleDateOfStart">
					
				</div>
			</div>
			<div class="rightControls">
			<input type="submit" class="btn">
			</div>
		</div>
	</div>
	</form>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">
	$(function(){
    	window.prettyPrint && prettyPrint();
        $('#moduleDateOfStart').datepicker({
        	format: 'dd-mm-yyyy',
        	weekStart:1
        });
    });
</script>
</html>