
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
				<div class="span3">
					<div class="btn-group" >
					  <button class="btn">prev</button>
					  <button class="btn">issue</button>
					  <button class="btn">next</button>
					 </div>
					<h5>Title</h5>
					<input id="title" type="text" placeholder="Title">
					<style type="text/css">
					  #title { width: 80%;}
					</style>
					<br>
					<h5>Description</h5>
					<textarea rows="7" input id="descriptionOfIssue" placeholder="Description"></textarea>
					<style type="text/css">
					  #descriptionOfIssue { width: 80%;}
					</style>
					<h5>Attached</h5>
				</div>
				<div class="span5">
					<a class="btn btn-info" id="newIssue" href="${api_url}/issue">New issue</a>
					<style type="text/css">
					  #newIssue { width: 90%;}
					</style>
					<h5>Issues</h5>
					<table class="table table-hover table-condensed">
						<td>${issues}</td>
					</table><br>					 
					<div class="btn-group" >
					  <button class="btn" >Assign parant</button>
					  <button class="btn" >Assign son</button>
					 </div>
					</div>
					<div class="span2">
					<br><br><br><br>
					<button class="btn btn-info">Select baseline</button><br><br>
					<button class="btn btn-info">Create baseline</button>
					</div>
		</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">

</script>
</html>