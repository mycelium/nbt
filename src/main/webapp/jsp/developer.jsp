
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
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/button.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/mytextarea.css"/>" />

<title>Developer interface</title>
</head>
<body onload="getInfo()">
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Developer interface</h3>
					<div class="span2">
						
						<h5>Tasks:</h5>
						<select id="taskTable"  class="width100" multiple onchange="getInfo()">
							<c:forEach items="${tasks}" var="task">
								<option value="${task.id}">${task.caption}</option>
							</c:forEach>
						</select>
						<button onclick="taskView()" class="btn width100">Task View</button>
						
					</div>
					
					<div class="span4">
					<h5>In things:</h5>
					<div id="redex1"></div>
					<br>
					<button class="btn width100" onclick="addModulesToTasks()">Add Module To Task</button>
					</div>
					
					<div class="span4">
					<button class="btn width100" onclick="newModule()">Create new Module</button>	
						<h5>Modules:</h5>
						<select id="moduleTable" class="width100" multiple >
							<c:forEach items="${modules}" var="module">
								<option id="module_${module.id}" value="${module.id}">${module.caption}</option>
							</c:forEach>
						</select>
						<button onclick="editModule()" class="btn width100">Edit module</button>
					</div>
				</div>
			</div>
						
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">

function newModule()
{
window.location="${api_url}/newModule";
}

function taskView()
{
	var val=document.getElementById("taskTable").value;
	if (val)
	window.location="${api_url}/task/"+val;
}

function editModule()
{
	var val=document.getElementById("moduleTable").value;
	if (val)
	window.location="${api_url}/module/"+val;
	
}

function addModulesToTasks()
{
if($('#taskTable').val()!=null && $('#moduleTable').val()!=null)
{
var dat = JSON.stringify({
    idTaskList : $('#taskTable').val(),
	idModuleList: $('#moduleTable').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href +"/addModuleToTask",
data:dat,
success: function(data){
getInfo();
if(getInfo)
	$("#moduleTable").find('option:selected').remove();
}
});
}
}


function getInfo()
{

if($('#taskTable').val()!=null)
{
	var dat = JSON.stringify({
    idTaskList : $('#taskTable').val(),
	idModuleList: $('#moduleTable').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href +"/getInfo",
data:dat,
success: function(data){
var p=data.modulesIdAndCaption;
var hrefString="";
for (var key in p) {
  if (p.hasOwnProperty(key)) {
    hrefString+="Module: <a href=\" developer/module/"+key+" \">"+p[key]+"</a><br>";
  }
}
//alert(hrefString);
$("#redex1").html("Task id:"+data.idTask+"<br>"+"Task Caption:"+data.captionTask+"<br>"+"Task Description:"+data.descriptionTask+"<br>"+
"Attached Modules:<br>"+hrefString+"");
return true;
},
error: function() {return false;}
});
}
}

		
</script>
</html>
