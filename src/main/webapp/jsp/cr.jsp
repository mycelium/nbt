
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
<script type="text/javascript"	src="<c:url value="/js/bootstrap-datepicker.js"/>"></script>

<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/jquery-ui.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/style.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/button.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/bootstrap.min.css"/>" />
<link type="text/css" rel="stylesheet" media="all"	href="<c:url value="/css/datepicker.css"/>" />

<title>Cr view</title>
</head>
<body>
	<div class="container content">
		<div class="container-fluid">
			<div class="row-fluid">
				<c:import url="/jsp/header.jsp" />
				<div class="span10">
					<h3>CR view</h3>
				</div><br>
				<div class="span5">
					<form action="${api_url}/cr/edit" method="post">
					<input id="crId" name="crId" type="hidden"  value="${crView.id}">
					
					<h5>Title</h5>
					<input id="crCaption" name="crCaption" type="text" placeholder="Title" value="${crView.caption}">
					<br>
					<h5>Description</h5>
					<textarea rows="7"  id="crDescription" name="crDescription"  placeholder="Description" >${crView.description}</textarea>
					<c:if test="${crView.pathToFile!=''}">
					<h5>Attached</h5>
					<a href="<c:url value="/img/cr/${crView.pathToFile}"/>">${crView.pathToFile}</a>
					<button class="btn btn-info" id="delFile" value="${crView.pathToFile}" onclick="${api_url}/cr/delFile"/>Delete</button>
					</c:if>	
					<br><br>
					<button class="btn " type="submit">Save changes</button>
					</form>
				</div>
				<div class="span5">
					<h5>Assigned issues</h5>
					<select multiple id="assIssues"  name="assIssues">
						<c:forEach items="${crView.issueIdList}" var="cr">
								<option value="${cr}">${cr}</option>
						</c:forEach>	 
					</select>	
								
					<button class="btn" onclick="delIssueFromCr('${api_url}')" id="removeissbut">Remove issue</button>
					
					<h5>All issues</h5>
					<select id="allIssues" multiple>
							<c:forEach items="${issues}" var="issue">
								<option value="${issue.id}">${issue.id}</option>
							</c:forEach>
						</select>					
					<div class="btn-group" >
					  <button class="btn" onclick="addIssuesToCr('${api_url}')" id="addisssbut">Add Issues</button>
					 </div>
					</div></div>
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
</body>

<script type="text/javascript">

function addIssuesToCr(urlValue)
{
var crId=[];

crId.push($('#crId').val());
var dat = JSON.stringify({
    idCRList : crId,
	idIssueList: $('#allIssues').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:urlValue+"/addIssueToCr",
data:dat,
success: function(data){

}
})
}
function delIssueFromCr(urlValue)
{
var crId=[];
alert(urlValue);
crId.push($('#crId').val());
var dat = JSON.stringify({
    idCRList : crId,
	idIssueList: $('#assIssues').val()
});
$.ajax({
type:"POST",
contentType:"application/json",
dataType:"json",
url:urlValue+"/delIssueFromCr",
data:dat,
success: function(data){
$('#assIssues').find('option:selected').remove();
}
})
}

</script>
</html>