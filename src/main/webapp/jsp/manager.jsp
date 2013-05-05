
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
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/mytextarea.css"/>" />

<title>Manager interface</title>
</head>
<body onload="getInfo()">
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Manager interface</h3>
					<div class="span2">
						
						<h5>CRs:</h5>
						<select id="crTable"  style="width: 100%" multiple onchange="getInfo()">
							<c:forEach items="${crs}" var="cr">
								<option value="${cr.id}">${cr.caption}</option>
							</c:forEach>
						</select>
						<button onclick="crView()" class="btn width100" id="editCr">Edit CR</button>
						
					</div>
					
					<div class="span4">
					<h5>Out things:</h5>
					<div id="redex1" ></div>
					<button class="btn" id="addtasktocrbut"  onclick="addTasksToCr()">Add task to CR </button>
					</div>
					
					<div class="span4">
					<a class="btn " id="newTask" href="${api_url}/newTask">Create new Task</a>	
						<h5>Tasks:</h5>
						<select id="taskTable" multiple >
							<c:forEach items="${tasks}" var="task">
								<option id="task_${task.id}" value="${task.id}">${task.caption}</option>
							</c:forEach>
						</select>
						<button onclick="editTask()" class="btn width100" id="editCr">Edit Task</button>
					</div>
				</div>
			</div>
						
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">
function crView()
{
	window.location="/nbt/site/manager/cr/"+document.getElementById("crTable").value;
}

function editTask()
{
	window.location="/nbt/site/manager/task/"+document.getElementById("taskTable").value;
}

/*function addTaskToCr()
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
}*/

function addTasksToCr()
{
if($('#crTable').val()!=null && $('#taskTable').val()!=null)
{
var dat = JSON.stringify({
    idCRList : $('#crTable').val(),
	idTaskList: $('#taskTable').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href +"/addTasksToCr",
data:dat,
success: function(data){
getInfo();
if(getInfo)
	$("#taskTable").find('option:selected').remove();
/*alert(data.assignedIssuesCaption);
$("#redex1").html("CR id:"+data.idCR+"\n"+"CR Caprion"+data.captionCR+"\n"+"CR Desription:"+data.descriptionCR+"\n"+
"Attached Issues:"+data.assignedIssuesCaption+"\n");
}
})/*.done(function() {alert("Issuses added to CR!");});*/
}
});
}
}

function getInfo()
{

if($('#crTable').val()!=null)
{
	var dat = JSON.stringify({
    idCRList : $('#crTable').val(),
	idTaskList: $('#taskTable').val()
});


$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href +"/getInfo",
data:dat,
success: function(data){
var p=data.tasksIdAndCaption;
var hrefString="";
for (var key in p) {
  if (p.hasOwnProperty(key)) {
    hrefString+="Task: <a href=\" manager/task/"+key+" \">"+p[key]+"</a><br>";
  }
}
//alert(hrefString);
$("#redex1").html("CR id:"+data.idCR+"<br>"+"CR Caption:"+data.captionCR+"<br>"+"CR Description:"+data.descriptionCR+"<br>"+
"Attached Tasks:<br>"+hrefString+"<br>");
return true;
},
error: function() {return false;}
});
}
}

		
</script>
</html>
