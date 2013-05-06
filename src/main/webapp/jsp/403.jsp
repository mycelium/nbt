
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript"	src="<c:url value="/js/bootstrap.min.js"/>"></script>

<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/button.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />

<title>Error 403</title>
</head>
<body>
<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>403 Error</h3><br>
					<h5>You don't have enough rights to access this page.</h5><br>
					<h5>Please,  <a class="btn" href="nbt/j_spring_security_logout"> Logout </a>  and login with correct user name.</h5>
</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
</html>