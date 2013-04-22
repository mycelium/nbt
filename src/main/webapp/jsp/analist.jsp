
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
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/button.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />

<title>Analyst interface</title>
</head>
<body onload="getInfo()">

	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>Analyst interface</h3>
					<div class="span2" >
						<br> <br> <a class="btn" id="newCr" href="${api_url}/cr/new">Create new CR</a> <br>
						<h5>CRs:</h5>
						<select id="crTable"  style="width: 100%"  multiple  onchange="getInfo()">
							<c:forEach items="${crs}" var="cr">
								<option value="${cr.id}">${cr.caption}</option>
							</c:forEach>
						</select>
						<a href="javascript:editCr()" class="btn" id="editCr">Edit CR</a>
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
									<textarea disabled id="redex1"  >In</textarea>
								</div>
								<div class="tab-pane" id="tab2">
									<textarea disabled id="redex2" >Out</textarea>
								</div>
							</div>
						</div>
						
						<button class="btn"  onclick="javascript:addIssuesToCr()" id="addIssueToCrbut">Add issue to CR </button>
					</div>

					<div class="span4">
						<a class="btn " id="newIssue" href="${api_url}/issue/new" >Create new Issue</a>
							<table class="table table-condensed">
								<tr>
									<td>New issues: ${countNewIssues}  </td>
								</tr>							
								<tr>
									<td>Not connected issues: ${countNotLinkedIssues} </td>
								</tr>
							</table>
						<h5>Read and marked issues:</h5>
						<select id="issueTable" multiple>
							<c:forEach items="${issues}" var="issue">
							<c:if test="${empty issue.attachedCRs}">
								<c:if test="${issue.marker==''}"><option class="issues" id="issue_${issue.id}" value="${issue.id}" style="font-weight:bold">${issue.caption}</option></c:if>
								<c:if test="${issue.marker!=''}"><option class="issues" id="issue_${issue.id}" value="${issue.id}" style="background:${issue.marker}">${issue.caption}</option></c:if>
								</c:if>
							</c:forEach>
						</select>
													
					<div class="btn-group">
						<a class="btn dropdown-toggle" data-toggle="dropdown" data-target="#">
						Mark
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
						<button class="btn" value="green" id="greenbut" onclick="javascript:addMark(this)">Green</button>
						<button class="btn" value="yellow" id="yellowbut" onclick="javascript:addMark(this)">Yellow</button>
						<button class="btn" value="red" id="redbut" onclick="javascript:addMark(this)">Red</button>		
						</ul>
					</div>
					<button class="btn" value="" onclick="javascript:addMark(this)">Unmark</button>
					<a href="javascript:editIssue()" class="btn">Edit Issue</a>
						</div>
				</div>
			</div>
						
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>
<script type="text/javascript">

function editCr()
{
	window.location="/nbt/site/analist/cr/"+document.getElementById("crTable").value;
}
function editIssue()
{
	window.location="/nbt/site/analist/issue/"+document.getElementById("issueTable").value;
}

function addMark(elem){
	
	alert(window.location.href);
var dat = JSON.stringify({
    idList : $('#issueTable').val(),
	marker : elem.value
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href + "/mark",
data:dat
}).done(function() { 
var issues=$('#issueTable').val(); 
for(var i=0;i<issues.length;++i)
{
	$('#issue_'+issues[i]).css('background',elem.value);	
}
});
}

function addIssuesToCr()
{
var dat = JSON.stringify({
    idCRList : $('#crTable').val(),
	idIssueList: $('#issueTable').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:window.location.href +"/addIssueToCr",
data:dat
}).done(function() {alert("Issuses added to CR!");});
}

function getInfo()
{
	var dat = JSON.stringify({
    idCRList : $('#crTable').val(),
	idIssueList: $('#issueTable').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"text",
url:window.location.href +"/getInfo",
data:dat,
success: function(response) {
$("#redex1").html(response );
}
});
}
		
</script>
</html>
