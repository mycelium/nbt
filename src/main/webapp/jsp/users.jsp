
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/bootstrap.min.css"/>" />

<title>Users</title>
</head>
<body>
	<div class="container content">

		<!-- Left Menu container, do not forget about active list member -->
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<!-- Main Container -->
				<div class="span10">
					<div class="topElements">
						<h2>Users</h2>
						<div class="rightControls">
							<a href="javascript:createNew()" class="bigLink">Add user</a>
						</div>
					</div>
					<table class="mainTable">
						<thead>
							<th>Login</th>
							<th>Last Name</th>
							<th>First Name</th>
							<th>E-mail</th>
						</thead>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.login}</td>
								<td>${user.lastName}</td>
								<td>${user.firstName}</td>
								<td>${user.email}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
	
	<div id="addUserDialog" style="display:none;" title="New user">
	<form id="addUserForm" action="" method="POST">
		<p>add inputs for form</p>
	</form>
</div>
<script type="text/javascript">
	function createNew() {
		$('#addUserDialog').dialog({
			title : "New User",
			width : 530,
			resizable : false,
			modal : true,
			buttons: {
	            'Add': function() {
					$('#addUserForm	').submit();
	            },
	            'Cancel': function() {
	                $(this).dialog('close');
	            }
	        }});
	}
</script>
</body>
</html>

