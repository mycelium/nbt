
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<c:url value="/site/manager" var="api_url" />

<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/js/bootstrap-datepicker.js"/>"></script>
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/button.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/datepicker.css"/>" />

<title>Cr view</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>CR view</h3>
				</div><br>
				<div class="span5">
					<form action="${api_url}/delTaskFromCr" method="get">
					<input id="crId" name="crId" type="hidden"  value="${crsView.id}">		
					<h5>Title</h5>
					<input id="title" type="text" placeholder="Title" value="${crsView.caption}">
					<br>
					<h5>Description</h5>
					<textarea rows="7" input id="descriptionOfIssue" placeholder="Description" >${crsView.description}</textarea>
					<h5>Out things</h5>
					<textarea rows="7" input id="outthing" placeholder="out" ></textarea>
				</div>
				<div class="span5">
					<h5>Assigned tasks</h5>
					<select multiple id="assTasks" class="width100" name="assTasks">
						<c:forEach items="${crsView.taskIdList}" var="task">
								<option value="${task}">${task}</option>
						</c:forEach>	 
					</select>	
								
					<button class="btn width100" type="submit">Remove task</button>
					</form>
					<h5>All tasks</h5>
					<select id="allIssues" class="width100" multiple>
							<c:forEach items="${taskss}" var="task">
								<option value="${task.id}">${task.caption}</option>
							</c:forEach>
						</select>					
					<button class="btn width100" onclick="addTaskToCr()">Add Task</button>
					</div></div>					
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">
function addTaskToCr()
{
var crId=[];
crId.push($('#crId').val());
var dat = JSON.stringify({
    idCRList : crId,
	idTaskList: $('#allIssues').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:"${api_url}/addTaskToCr",
data:dat
}).done(function() {});
}

</script>
</html>