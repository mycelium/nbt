<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/site" var="api_url" />

<div class="span2">
	<ul class="nav nav-list">
		<li class="nav-header">Menu</li>
		<li><a href="/nbt/site/main">Home</a></li>
		<li class="divider"/>
		<li><a href="${api_url}/main/newIssue">New issue</a></li>
		<li><a href="${api_url}/analist">Analyst</a></li>
		<li><a href="${api_url}/manager">Manager</a></li>
		<li><a href="${api_url}/developer">Developer</a></li>
		<li><a href="${api_url}/user">Users</a></li>
		<li class="divider"/>
		<li><a href="${api_url}/help">Help</a></li>
		<li><a href="nbt/j_spring_security_logout"> Logout</a></li>
	</ul>
</div>
