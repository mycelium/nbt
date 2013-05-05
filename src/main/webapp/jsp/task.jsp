
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
<script type="text/javascript"	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/js/bootstrap-datepicker.js"/>"></script>

<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/datepicker.css"/>" />

<title>Task view</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<form id="editTask" name="editTask" action="${api_url}/manager/editTask" method="POST">
				<div class="span10">
				
					<h3>Task View</h3>
				</div><br>
				<div class="span5">
				<input id="taskId" name="taskId" type="hidden" value="${taskView.id}">
					<h5>Title</h5>
					<input id="taskCaption" name="taskCaption" type="text" placeholder="Title" value="${taskView.caption}" required>		
					<br>
					<h5>Description</h5>
					<textarea rows="7"  id="taskDescription" name="taskDescription" placeholder="Description"> ${taskView.description}</textarea>
					<h5>CR</h5>
					<select id="crId" name="crId" multiple>
					<c:forEach items="${taskView.attachedCRs}" var="cr">
						<option>${cr}</option>
					</c:forEach>
					</select>
					
				</div>
				<div class="span5">
					<h5>Date of creation</h5>
					<input type="text" id="taskDateOfStart" name="taskDateOfStart" value="${dateOfCreation}"> 
					
				</div>
			<div class="rightControls">
			<input type="submit" class="btn btn-info">
			</div>
			</form>
			</div>

		</div>
	</div>
	
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">
	$(function(){
    	window.prettyPrint && prettyPrint();
        $('#taskDateOfStart').datepicker({
        	format: 'dd-mm-yyyy',
        	weekStart:1
        });
    });
</script>
</html>