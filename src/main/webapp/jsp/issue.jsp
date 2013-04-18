
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

<title>Issue view</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Issue view</h3>
				<br>
				<div class="span6">
					<h4>Issue ${issueView.caption}</h4>
					  <br>
					
					<h5>Title</h5>
					<input type="text" placeholder="Title" value="${issueView.caption}"><br>
					<h5>Description</h5>
					<textarea rows="7" input id="descriptionOfIssue" placeholder="Description">${issueView.description}</textarea>
					<c:if test="${not empty issueView.pathToFile}">
						<h5>Attached</h5>
						<img src="<c:url value="/${issueView.pathToFile}"/>" width="86%">
					</c:if>
					<br><br>
				</div>
					<div class="span4">
					<button class="btn btn-info">Add mark</button><br><br>
					<button class="btn btn-info">Add to read list</button><br><br>
					</div>
					
				</div>
		</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>

</html>