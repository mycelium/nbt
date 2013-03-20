<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/site" var="api_url" />

<div class="span2">
	<ul class="nav nav-list">
		<li class="nav-header">Menu</li>
		<li><a href="/nbt">Home</a></li>
		<li class="divider"/>
		<li><a href="${api_url}/analist">Analist</a></li>
		<li><a href="${api_url}/user">Users</a></li>
		<li class="divider"/>
		<li><a href="${api_url}/help">Help</a></li>
	</ul>
</div>
