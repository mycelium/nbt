
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
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-datepicker.js"/>"></script>

<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/datepicker.css"/>" />

<title>New CR</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
				<form id="newCr" name="newCr" action="${api_url}/analist/cr/add" method="POST" enctype="multipart/form-data" onsubmit="return validate()">
					<h3>Create New CR</h3>
				</div><br>
				<div class="span5">
					<h5>Title</h5>
					<input id="crCaption" name="crCaption" type="text" placeholder="Title" required>		
					<br>
					<h5>Author</h5>
					<input id="crAuthor" name="crAuthor" type="text" placeholder="Author">		
					<br>
					<h5>Description</h5>
					<textarea rows="7" input id="crDescription" name="crDescription" placeholder="Description"></textarea>
					<h5>Parent CR</h5>
					<select id="crParentId" name="crParentId">
						<option>1</option>
					</select>
					<h5>Priority</h5>
					<select id="crPriority" name="crPriority">
						<option>Low</option>
						<option>Normal</option>
						<option>As Fast As You Can</option>
					</select>
				</div>
				<div class="span5">
					<h5>Date of creation</h5>
					<input type="text" id="crDateOfStart" name="crDateOfStart">
					<h5>Date of finish</h5>
					<input type="text" id="crDateOfFinish" name="crDateOfFinish">
					<h5>Hours</h5>
					<input id="crHours" name="crHours" type="text" placeholder="Hours">
					<h5>Responsible persons</h5>
					<select id="crWatchers" name="crWatchers" multiple>
					<c:forEach items="${users}" var="user">
						<option>${user.login}</option>
					</c:forEach>

					</select>
					<h5>Issue</h5>
					<select id="issuesId" name="issuesId" multiple>
					<c:forEach items="${issues}" var="issue">
					<option value="${issue.id}">${issue.caption}</option>
					</c:forEach>
					</select>
					<h5>Attach</h5>
					<input type="file" name="file">
				</div>
			</div>
			<div class="rightControls">
			<input id="but1" type="submit" class="btn" value="Create">
			</div>
		</div>
	</div>
	</form>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">
	$(function(){
    	window.prettyPrint && prettyPrint();
        $('#crDateOfFinish').datepicker({
        	format: 'dd-mm-yyyy',
        	weekStart:1
        });
		$('#crDateOfStart').datepicker({
        	format: 'dd-mm-yyyy',
        	weekStart:1
        });
    });
	
	function validate(){
		var start=document.getElementById("crDateOfStart");
		if (start.value==null) 
		{ 
		alert("Enter valid operands !");
		return false;
		}
		}
</script>
</html>