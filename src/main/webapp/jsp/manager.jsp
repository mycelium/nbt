
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
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/button.css"/>" />

<title>Manager interface</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Manager interface</h3>
					<div class="span2">
						
						<h5>CRs:</h5>
						<select id="crTable" class="width100" multiple>
							<c:forEach items="${crs}" var="cr">
								<option value="${cr.id}">${cr.caption}</option>
							</c:forEach>
						</select>
						<button onclick="crView()" class="btn width100">CR view</a>
						
					</div>
					
					<div class="span4">
					<h5>Out things:</h5>
					<textarea id="out" class="mytext" disabled></textarea>
					<button class="btn width100" onclick="addTaskToCr()">Add task to CR</button>
					</div>
					
					<div class="span4">
					<button class="btn width100" onclick="newTask()">Create new Task</button>	
						<h5>Tasks:</h5>
						<select id="taskTable" class="width100" multiple >
							<c:forEach items="${tasks}" var="task">
								<option id="task_${task.id}" value="${task.id}">${task.caption}</option>
							</c:forEach>
						</select>
						<button onclick="editTask()" class="btn width100">Edit task</button>
					</div>
				</div>
			</div>
						
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">

function newTask()
{
window.location="${api_url}/newTask";
}

function crView()
{
	var val=document.getElementById("crTable").value;
	if (val)
	window.location="${api_url}/cr/"+val;
}

function editTask()
{
	var val=document.getElementById("taskTable").value;
	if (val)
	window.location="${api_url}/task/"+val;
	
}

function addTaskToCr()
{
var dat = JSON.stringify({
    idCRList : $('#crTable').val(),
	idTaskList: $('#taskTable').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href +"/addTaskToCr",
data:dat
}).done(function() {alert("Tasks added to CR!");});
}
		
</script>
</html>
