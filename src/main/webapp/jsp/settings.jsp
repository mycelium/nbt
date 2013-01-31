
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

<title>Profile</title>
</head>
<body>
	<div class="container content">

		<!-- Left Menu container, do not forget about active list member -->
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<!-- Main Container -->
				<div class="span8">
					<h3>Change password</h3>
				</div>
				<div class="span5">
					<h5>Old password</h5>
					<input id="Old password" type="text" placeholder="Old">
					<h5>New password</h5>
					<input id="New password" type="text" placeholder="New">
					<button class='btn'>Save</button>
					</br>

					<div class="span8">
						<h3>Change name</h3>
					</div>
					</br>
					<h5>New name</h5>
					<input id="New name" type="text" placeholder="New">
					<button class='btn'>Save</button>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('.btn').click(function() {

		});
	});
</script>
</html>