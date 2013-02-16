<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/site" var="api_url" />

<div class="span2">
	<ul class="nav nav-list">
		<li class="nav-header">List header</li>
		<li><a href="/nbt">Home</a></li>
		<li><a href="${api_url}/hello">Hello page</a></li>
		<li><a href="${api_url}/issue/new">New Issue</a></li>
		<li class="nav-header">Another list header</li>
		<li><a href="${api_url}/profile">Profile</a></li>
		<li><a href="${api_url}/profile/settings">Settings</a></li>
		<li><a href="${api_url}/user">Users</a></li>
		<li class="divider"></li>
		<li><a href="help">Help</a></li>
	</ul>
</div>
