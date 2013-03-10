<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<c:url value="/site/analist" var="api_url" />
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="screen"	href="<c:url value="/css/bootstrap.min.css"/>">
<script type="text/javascript" src="<c:url value="js/jquery.js"/>"></script>
<script type="text/javascript"	src="<c:url value="js/bootstrap.min.js"/>"></script>

<title>Analyst interface</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Analyst interface</h3>
					<br>
					<div class="span2">
					<table class="table table-hover table-condensed">
						<thead>
							<th>CRs</th>
						</thead>
						<c:forEach items="${analist}" var="crs">
						<td>${crs.caption}</td>
						</c:forEach>
					</table><br>
					<a href="${api_url}/cr" class="btn btn-info">Add CR</a>
					</div>
					<div class="span4" >
					<div class="btn-group" >
					  <button class="btn " >"In" things</button>
					  <button class="btn" >"Out" things</button>
					  </div>
					<br>
					<textarea disabled id="redex"></textarea>
					<style type="text/css">
					  #redex { resize: none;
							   width: 93%;
							   height: 150px;
								}
					</style><br>
					<button class="btn btn-info">Edit CR</button><br><br>
					<button class="btn btn-info" id="editcr">Add issue to CR</button>
					<style type="text/css">
					  #editcr { width: 100%;}
					</style>
					
					</div>
					
					<div class="span4">
					<a class="btn btn-info" id="newIssue" href="${api_url}/issue">New issue</a>
					<style type="text/css">
					  #newIssue { width: 130%;}
					</style>
					<h5>Read and marked issues</h5>
					<table class="table table-hover table-condensed">
						<td>${issues}</td>
					</table><br>					 
					<div class="btn-group" >
					  <button class="btn btn-info" >Delete all with connections</button>
					  <button class="btn btn-info" >Delete marked</button>
					 </div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
	
					<div id="addCrDialog" style="display:none;" title="New cr">
					<form id="addCrForm" action="${api_url}/new" method="POST">
					<table class="table">
		<thead>
		<th>Caption</th>
		<th>Author</th>
		</thead>
			<tr>
			<td><input class="input-small"  name="caption"></td>
			<td><input class="input-small"  name="author"></td>
			
			</tr>
			</table>
					
					</form>
				</div>
<script type="text/javascript">

function createCr() {
	$('#addCrDialog').dialog({
			title : "New Cr",
			width : 600	,
			resizable : false,
			modal : true,
			buttons: {
	            'Add': function() {
					$('#addCrForm').submit();
	            },
	            'Cancel': function() {
	                $(this).dialog('close');
	            }
	        }});
	}
</script>
</body>
</html>
