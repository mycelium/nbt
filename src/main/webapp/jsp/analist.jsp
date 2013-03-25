
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
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/css/bootstrap.min.css"/>" />

<title>Analyst interface</title>
</head>
<body>

	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Analyst interface</h3>
					<div class="span2">
						<br> <br> <a class="btn" id="newCr" href="${api_url}/cr/new">Create new CR</a> <br>
						<h5>CRs:</h5>
						<select id="crTable" multiple style="width: 100%">
							<c:forEach items="${crs}" var="cr">
								<option>${cr.caption}</option>
							 <a href="${api_url}/cr/${cr.id}" class="btn btn-info">Edit CR</a><br>
							 </c:forEach>
						</select>
					</div>

					<div class="span4">
						<br> <br>
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab1" data-toggle="tab">In things</a></li>
								<li><a href="#tab2" data-toggle="tab">Out things</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<textarea disabled id="redex1">In</textarea>
								</div>
								<div class="tab-pane" id="tab2">
									<textarea disabled id="redex2">Out</textarea>
								</div>
							</div>
						</div>
						<br>
						<br>
						<button class="btn btn-info" id="editcr">Add issue to CR</button>
					</div>

					<div class="span4">

						<a class="btn input-large" id="newIssue" href="${api_url}/issue/view"> new Issue</a>
							<table class="table table-condensed">
								<tr>
									<td>New issue # </td>
								</tr>							
								<tr>
									<td>Not connected issue #</td>
								</tr>
							</table>
						<h5>Read and marked issues:</h5>
						
						<h5>Issues:</h5>
						<select id="issueTable" multiple style="width: 100%">
							<c:forEach items="${issues}" var="issue">
								<option>${issue.caption}</option>
							</c:forEach>
						</select>
						<br>
						<button class="btn btn-info">Delete marked</button>
						<br> <br>
						<button class="btn btn-info" id="delAll">Delete all with connections</button>
					</div>
				</div>
			</div>
						
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />

	<div id="dialogHello" style="display: none"></div>

</body>

</html>