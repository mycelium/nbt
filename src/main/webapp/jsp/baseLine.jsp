
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<script type="text/javascript" src="<c:url value="/js/jquery.jstree.js"/>"></script>
<body>

<div id="tree"></div>
</body>
<script>
	$(function () {
	    $("#tree").jstree({
	        "xml_data" : {
	            "ajax" : {
	                "url" : "/xml_nest.xml"
	            },
	            "xsl" : "nest"
	        },
	        "plugins" : [ "themes", "xml_data" ]
	    });
	});
</script>
</html>