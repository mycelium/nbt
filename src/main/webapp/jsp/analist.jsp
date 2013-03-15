	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ page pageEncoding="UTF-8"%>
	<%@ page trimDirectiveWhitespaces="true"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
	<html>
	<c:url value="/site/analist" var="api_url" />

	<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
	<script type="text/javascript"	src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
	<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
	<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />

	<title>Analyst interface</title>
	</head>
	<body>

		<div class="container content">
			<div class="container-fluid">
				<div class="row-fluid">
					<c:import url="/jsp/header.jsp" />
					<div class="span10">
						<h3>Analyst interface</h3>
						
						
						<div class="span2"><br><br>
							<a  class="btn" id="newCr">Create new CR</a>
							<br>
							<table class="table table-hover table-condensed">
								<thead>
									<th>CRs:</th>
								</thead>
								<td>${crs}</td>
								<c:forEach items="${analist}" var="crs">
								<td>${crs.caption}</td>
								</c:forEach>
							</table>
						</div>
					
						<div class="span4"><br><br>
							<div class="tabbable"> 
								<ul class="nav nav-tabs">
								<li class="active"><a href="#tab1" data-toggle="tab">In things</a></li>
								<li><a href="#tab2" data-toggle="tab">Out things</a></li>
								</ul>
								<div class="tab-content">
								<div class="tab-pane active" id="tab1">
								  <textarea disabled id="redex1">In</textarea>
									<style type="text/css">
									#redex1 { resize: none;
									   width: 93%;
									   height: 150px;
										}
									</style>
								</div>
								<div class="tab-pane" id="tab2">
								  <textarea disabled id="redex2">Out</textarea>
									<style type="text/css">
									#redex2 { resize: none;
									   width: 93%;
									   height: 150px;
										}
									</style>
								</div>
								</div>
							</div>
							<br>
							<a href="${api_url}/cr" class="btn btn-info">Edit CR</a><br><br>
							<button class="btn btn-info" id="editcr">Add issue to CR</button>
							<style type="text/css">
							#editcr { width: 100%;}
							</style>
						
						</div>
						
						<div class="span4">
						
						<a class="btn" id="newIssue" href="${api_url}/issue">
						<table class="table table-condensed"> 
						<thead>
							<th>New issue</th>
						</thead>
						<tr>
							<td>New issue #<td></tr>
							<tr><td>Not connected issue #<td></tr>
						</table>
						</a>
						
						<style type="text/css">
						  #newIssue { width: 100%; height: 84px; }
						  #newCr { width: 70%; }
						  #delAll { width: 100%; }
						</style>
						
						<h5>Read and marked issues:</h5>
						<table class="table table-hover table-condensed">
							<td>${issues}</td>
						</table><br>					 
						<button class="btn btn-info">Delete marked</button><br><br>
						<button class="btn btn-info" id="delAll">Delete all with connections</button>
						</div>
						
						
						</div>
			
			
					</div>
				</div>
			</div>
		</div>
		<c:import url="/jsp/footer.jsp" />
		
		<div id="dialogHello" style="display:none">
		</div>
		
	</body>
	<script type="text/javascript">
		var xmlhttp = new XMLHttpRequest();

		function showHelloDialog() {
			var name = $("#inputName").val();
			if (!name) {
				alert('Empty name!');
				return;
			}
			$.getJSON(window.location.href + "/greeting/" + name, {},
					function(data) {
						if (data.greeting) {
							$("#dialogHello")
									.append("<p>" + data.greeting + "</p>");
						} else {
							alert('No data returned!');
						}
					});
			$("#dialogHello").dialog({
				modal : true,
				width : 300,
				height : 150,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	</script>
	</html>