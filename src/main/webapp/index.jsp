<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="screen"
	href="<c:url value="/css/bootstrap.min.css"/>">
<!-- 
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	 -->
<script type="text/javascript" src="<c:url value="js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="js/bootstrap.min.js"/>"></script>

<title>NBT</title>
</head>
<c:url value="/site" var="api_url" />

<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span8">
					<h3>Welcome</h3>
					
					</div>
				
				
			</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
</html>
