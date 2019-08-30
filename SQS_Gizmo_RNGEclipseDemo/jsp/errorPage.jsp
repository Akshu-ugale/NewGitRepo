<%@ page isErrorPage="true" %>  
<html>
<head>
<title>Demo for RNG Tesing by Gaming Testing Laboratory</title>
</head>
<form action="inputForm.jsp" method="post">
<body bgcolor="#E6E6FA">

<table border="0" width ="100%" bgcolor="" >
<tr height="100%">
<td width="20%" height="100%">


<img src="images/tomcat.GIF" style="width: 60px; height: 48px;"/>
</td>
<td width="80%">
<h1>SQS's Gizmo for RNG</h1>
</td>
</tr>
<tr>
<td colspan="2" width="100%">
<br>
<br>
<b>Sorry an exception occured!</b>
<br>
  <br>
<b> 
<% if(request.getParameter("errorMessage") != null && !request.getParameter("errorMessage").equals(""))
	{
%>
		d:<%=request.getParameter("errorMessage").toString()%>
<%	}
	else if(exception != null) 
	{
%>
		Exception is: <%= exception %> 
<%	}
	else if(request.getAttribute("errorMessage") != null && !request.getAttribute("errorMessage").equals(""))
	{
%>
		<%=request.getAttribute("errorMessage").toString()%>
<%	}
	%>

</b>

</td>
</tr>
<tr>
<td colspan="2" width="100%">
&nbsp;
</td>
</tr>
<tr>
<td colspan="2" width="100%">
&nbsp;
</td>
</tr>
<tr>
<td colspan="2" width="100%"><center>
<button type="submit" value="backSubmit">Home Page</button><center>
</td>
</tr>


</table>
</td>
</tr>
</table>
</head>
</body>
</form>
</html>
