
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<c:url value="/site/user" var="api_url" />

<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>
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
						<h3>Users</h3>
					<br>
					<table class="table table-hover table-condensed">
						<thead>
							<th>Login</th>
							<th>Last Name</th>
							<th>First Name</th>
							<th>E-mail</th>
							<th>Operation</th>
						</thead>
						<c:forEach items="${users}" var="user">
							<tr id="user_${user.id}">
								<td><a href="${api_url}/${user.id}">${user.login}</a></td>
								<td>${user.lastName}</td>
								<td>${user.firstName}</td>
								<td>${user.email}</td>
								<td><input class="btn btn-info input-xlarge" type="button" value="Delete" onclick="deleteUser('${user.id}', '${user.login}')"> </td>
							</tr>
						</c:forEach>
					</table>
				</div>
			  <div class="rightControls">
			  <a href="javascript:createNew()" class="btn input-xlarge">Add user</a>
			  </div>
						
			</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
	
	<div id="addUserDialog"  title="New user">
	<form id="addUserForm" action="${api_url}/new" method="POST">
		<table class="table">
		<thead>
		<th>Login</th>
		<th>Last Name</th>
		<th>First Name</th>
		<th>E-mail</th>
		</thead>
			<tr>
			<td><input class="input-small" placeholder="Login" id="login" name="login"></td>
			<td><input class="input-small" placeholder="Last Name" id="lastName" name="lastName"></td>
			<td><input class="input-small" placeholder="First Name" id="firstName" name="firstName"></td>
			<td><input class="input-small" placeholder="E-mail" id="email" name="email"></td>
			<td><input type="password" class="input-small" placeholder="Password" id="password" name="password"></td>
			</tr>
			</table>
	</form>
</div>
<script type="text/javascript">
document.getElementById('addUserDialog').style.display = 'none';

var xmlhttp = new XMLHttpRequest();
	function createNew() {
		$('#addUserDialog').dialog({
			title : "New User",
			width : 600	,
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
	function deleteUser(id, login){
		if(confirm('Are you shure to delete user with login "'+login+'" ?')){
			xmlhttp.open('DELETE', window.location.href + '/' + id, true);
            xmlhttp.send();
            $("#user_"+id).remove();
		}
	}
</script>
</body>
</html>

