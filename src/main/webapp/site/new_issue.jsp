    <%@ page contentType="text/html;charset=UTF-8" %>
        <%@ page pageEncoding="UTF-8" %>
        <%@ page trimDirectiveWhitespaces="true" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
        <html>
        <c:url value="/site" var="api_url"/>

        <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap-datepicker.js"/>"></script>

        <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/jquery-ui.css"/>" />
        <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/style.css"/>"/>
        <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>
        <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/datepicker.css"/>"/>

        <title>New Issue</title>
        </head>
        <body>
        <div class="container content">
        <div id="dialogHello" style="display:none" title="Hello!" >

        </div>
        <!-- Left Menu container, do not forget about active list member -->
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span2">
                        <ul class="nav nav-list">
                            <li class="nav-header">List header</li>
                            <li><a href="/nbt">Home</a></li>
                            <li><a href="${api_url}/hello.jsp">Hello page</a></li>
                            <li><a href="${api_url}/template.jsp">Show template</a></li>
                            <li class="active"><a href="${api_url}/new_issue.jsp">New Issue</a> </li>
                            <li class="nav-header">Another list header</li>
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Settings</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>

        <!-- Main Container -->
                    <div class="span8"><h3>Create New Issue</h3></div>
                    <div class="span5">
                        <h5>CR#</h5>
                        <input id="numberCR" type="text" placeholder="CR number">
                        <h5>Issue Type</h5>
                        <select id="issueType">
                            <option>Core Error</option>
                            <option>UI Error</option>
                            <option>User's Request</option>
                        </select>
                        <h5>Subject of Issue</h5>
                        <input id="subjectOfIssue" type="text" placeholder="Subject">
                        <h5>Description of Issue</h5>
                        <textarea rows="7" input id="descriptionOfIssue" placeholder="Description"></textarea>
                        <input id="sumbitButton" type="button" class="btn btn-info btn-large" value="submit" OnClick="showHelloDialog">



                            <p><form name="Exercise_2"><b>Exercise 2:</b><br><br>
                            <script language="Javascript"><!--
                            ourText = "Welcome to our website, ";
                            // --></script>
                            Type your Name: <input name="visitor_name"><br>
                            <input name="showMeArea" readonly="true" size="30">
                            <input type="button" class="btn btn-info" value="Show Message!"
                            OnClick="showMeArea.value=ourText + visitor_name.value + '!';">
                            </form></p>




                        <script type="text/javascript">
                            $(function(){
                                $('sumbitButton').onclick({
                                alert('go to hell');
                                });
                            });
                        </script>


                    </div>
                    <div class="span5">
                        <h5>Parent CR</h5>
                        <input id="parentCR" type="text" placeholder="Parent">
                        <h5>Priority</h5>
                        <select id="priorityType">
                            <option>Low</option>
                            <option>Normal</option>
                            <option>As Fast As You Can</option>
                        </select>
                        <h5>Date of finish</h5>
                        <input type="text" class="span4"  id="dp1">
                        <script type="text/javascript">
                            $(function(){
                            window.prettyPrint && prettyPrint();
                            $('#dp1').datepicker({
                            format: 'dd-mm-yyyy',
                            weekStart:1
                            });
                            });

                        </script>

                    </div>

                </div>
            </div>

        </div>

        </body>
        </html>