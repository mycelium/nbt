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
				<div class="span8 btn-group">
					<h3>The Analyst interface</h3>
					<br>
					<button class="btn input-xlarge">Edit</button>
					<button class="btn input-xlarge">Push</button>
					</div>
				<div class="span4">
					<h5>Baseline #1</h5>	
					<input id="cr1" type="text" class="input-xlarge" placeholder="CR1">
                    <textarea rows="3" input id="showCR1"  type="text" class="input-xlarge"></textarea>
				</div>
				<div class="span2"><br><br>
				<button class="btn input-small">Deffects</button><br><br>
				<button class="btn input-small">Tasks</button>
				</div>
				<div class="span1"><br><br>
				<h5>Completion</h5>
				<div class="progress">
				<div class="bar" style="width: 60%;"></div>
				</div>
				</div>
				
				<div class="span2 offset1">
				<h5>Group CR by</h5>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="radio1" value="baseline" checked>Baseline</label>	
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="radio2" value="issue">Issue</label><br><br><br>				  
				
				<h5>Inbox</h5>
				<table class="table table-hover table-condensed">
						<thead>
							<th>Id</th>
							<th>Name</th>
						</thead>
				<tr>
					<td>01</td>
					<td>Oooh...</td>
				</table>
				
				</div>
				
				
			</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
</html>
