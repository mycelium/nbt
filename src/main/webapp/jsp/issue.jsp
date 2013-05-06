
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

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

<title>Issue view</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Issue view</h3>
				<div class="span6">
					<h4>Issue ${issueView.caption}</h4>		
					<input type="hidden" id="issId"value="${issueView.id}">
					<h5>Title</h5>
					<input type="text" placeholder="Title" value="${issueView.caption}"><br>
					<h5>Description</h5>
					<textarea rows="7" input id="descriptionOfIssue" placeholder="Description">${issueView.description}</textarea>
					<h5>Marker</h5>
					<input id="markercol"type="text" value="${issueView.marker}" style="background:${issueView.marker}"><br>
					<c:if test="${issueView.pathToFile!='img/issue/'}">
						<h5>Attached</h5>
						<img src="<c:url value="/${issueView.pathToFile}"/>" class="srcimg">
					</c:if>
					<br><br>
				</div>
					<div class="span4">
					<div class="btn-group">
						<a class="btn dropdown-toggle" data-toggle="dropdown" data-target="#">
						Mark
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
						<button value="green" class="greenbut" onclick="addMark(this)">Green</button>
						<button value="yellow" class="yellowbut" onclick="addMark(this)">Yellow</button>
						<button value="red" class="redbut" onclick="addMark(this)">Red</button>		
						</ul>
					</div>
					<button class="btn" value="" onclick="addMark(this)">Unmark</button><br><br>
					</div>
					
				</div>
		</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">
function addMark(elem){
var crId=[];
crId.push($('#issId').val());
var dat = JSON.stringify({
    idList : crId,
	marker : elem.value
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url: "/nbt/site/analist/mark",
data:dat
}).done(function() { 
	$('#markercol').css('background',elem.value);	
});
}
</script>
</html>