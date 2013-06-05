
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

<title>New Issue</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
				<form id="newIssue" name="newIssue" action="${api_url}/main/newIssue/add" method="POST" enctype="multipart/form-data">
					<h3>Create New Issue</h3>
				</div><br>
				<div class="span5">

					<h5>Title</h5>
					<input id="issueCaption" name="issueCaption" type="text" placeholder="Title" required>		
					<br>
					<h5>Description</h5>
					<textarea rows="7" input id="issueDescription" name="issueDescription" placeholder="Description"></textarea>
					<h5>Reporter</h5>
					<select id="issueReporter" name="issueReporter">
					<c:forEach items="${users}" var="user">
						<option>${user.login}</option>
					</c:forEach>
					</select>
					
					<br>
					<h5>Assignees</h5>
					<select id="issueAssignees" name="issueAssignees" multiple>
					<c:forEach items="${users}" var="user">
						<option>${user.login}</option>
					</c:forEach>
					</select>
					<h5>Responsible persons</h5>
					<select id="issueWatchers" name="issueWatchers" multiple>
					<c:forEach items="${users}" var="user">
						<option>${user.login}</option>
					</c:forEach>
					</select>
					
					</div>
					<div class="span5">			
					<h5>Type</h5>
					<select id="issueType" name="issueType">
						<option>Business requirement</option>
						<option>Inquiry</option>
						<option>Bug revealed</option>
					</select>
					<h5>Status</h5>
					<select id="issueStatus" name="issueStatus">
						<option>New</option>
						<option>In work</option>
						<option>Solved</option>
						<option>Feedback</option>
						<option>Closed</option>
					</select>
					<h5>Priority</h5>
					<select id="issuePriority" name="issuePriority">
						<option>Low</option>
						<option>Normal</option>
						<option>As Fast As You Can</option>
					</select>
					<h5>Creation date</h5>
					<input type="text" class="span4" id="issueCreationDate" name="issueCreationDate">
					<h5>Environment</h5>
					<input id="issueEnvironment" name="issueEnvironment" type="text">	
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
        $('#issueCreationDate').datepicker({
        	format: 'dd-mm-yyyy',
        	weekStart:1
        });
		$('#issueModificationDate').datepicker({
        	format: 'dd-mm-yyyy',
        	weekStart:1
        });
    });
</script>
</html>