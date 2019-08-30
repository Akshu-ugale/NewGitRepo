<%@page contentType="text/html"%>

<%@taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://cewolf.sourceforge.net/taglib/cewolf.tld" prefix="cewolf" %>

<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>   
<%@page import="java.io.*"%> 
<%@page import="java.awt.*" %>
<%@page import="java.util.*"%>
<%@page import="de.laures.cewolf.*"%>
<%@page import="de.laures.cewolf.cpp.*"%>
<%@page import="de.laures.cewolf.example.*"%>
<%@page import="de.laures.cewolf.links.*"%>
<%@page import="de.laures.cewolf.taglib.CewolfChartFactory" %>
<%@page import="de.laures.cewolf.tooltips.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.event.ChartProgressEvent.*" %>
<%@page import="org.jfree.chart.event.ChartProgressListener.*" %>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.*"%>
<%@page import="org.jfree.data.category.*"%>
<%@page import="org.jfree.data.gantt.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.time.*"%>
<%@page import="org.jfree.data.xy.*"%>

<%@page errorPage="errorPage.jsp" %> 

<jsp:useBean id="obj1" class="scaleddatamathstests.InputBaseData" scope="session"/>
<jsp:useBean id="obj" class="scaleddatamathstests.RunsUpRunsDownTest" scope="session"/>
<jsp:useBean id="pageViews" class="graphs.GraphResult" scope="session"/>
<jsp:useBean id="seriesPaint" class="de.laures.cewolf.cpp.SeriesPaintProcessor" />
<jsp:useBean id="barRenderer" class="de.laures.cewolf.cpp.BarRendererProcessor" />

<jsp:useBean id="pieEnhancer" class="de.laures.cewolf.cpp.PieEnhancer" />


<form action="inputForm.jsp?isBack=Y" method="post">

<% 
if(request.getAttribute("errorMessage") != null && !request.getAttribute("errorMessage").equals(""))
{ 
%>
	<jsp:forward page="errorPage.jsp" /> 
<%
}

if(request.getAttribute("gName") != null)
{
	obj1.SetGameName(request.getAttribute("gName").toString());
}
if(request.getAttribute("VName") != null)
{
	obj1.SetGameVersion(request.getAttribute("VName").toString());
}
if(request.getAttribute("gName") != null)
{
	obj1.SetGameName(request.getAttribute("gName").toString());
}
if(request.getAttribute("alpha") != null)
{
	obj1.SetAlpha(request.getAttribute("alpha").toString());
}
if(request.getAttribute("numbersPerDraw") != null && !request.getAttribute("numbersPerDraw").equals(""))
{
	obj1.SetNumbersPerDraw(Integer.parseInt(request.getAttribute("numbersPerDraw").toString()));
}
if(request.getAttribute("numberType") != null && !request.getAttribute("numberType").equals(""))
{
	obj1.SetNumberType(request.getAttribute("numberType").toString());
}
if(request.getAttribute("testName") != null)
{
	String[] testNamesList = (String [])request.getAttribute("testName");
	obj1.SetTestNames(testNamesList);
}
if(request.getAttribute("filenameupload") != null)
{
	obj1.SetFileNameUpload(request.getAttribute("filenameupload").toString()); 
	
}

obj1.performCalculation();
if(!obj1.GetErrorMessage().equals("None") && !obj1.GetErrorMessage().equals(""))
{
%>	
<jsp:forward page="errorPage.jsp" > 
<jsp:param name="errorMessage" value="<%=obj1.GetErrorMessage()%>" />
</jsp:forward>
<%
}
else
{
%>
<html>
<head>
<title>Demo for RNG Tesing by Gaming Testing Laboratory</title>
</head>
<body bgcolor=white>

<table border="1" width ="100%" bgcolor="#E6E6FA" height="100%">
<tr>
<td width="100%">

<table align = "top" border="0" width="100%" >
<tr align = "top" width="100%" height="100%">
<td align="center" width="10%">
<img src="images/tomcat.GIF" style="width: 60px; height: 48px;"/>
</td>
<td width="90%">
<h1>SQS's Gizmo for RNG</h1>
</td>
</tr>

</table>
<table border="1" width="100%">

<tr width="100%">
<td width="100%" colspan="2">
<center><b><font size="5">Result of the tests</font></b></center>
</td>

</tr>
<tr width="100%">
<td width="10%">
<b>Game Name    :</b>
</td>
<td width="90%">
<b><%=obj1.GetGameName()%>
<input type= "hidden" name="gName_b" value="<%=obj1.GetGameName()%>"></b>
</td>
</tr>

<tr width="100%">
<td width="10%">
<b>Game Version :</b>
</td>
<td width="90%" align="left">
<b><%=obj1.GetGameVersion()%>
<input type= "hidden" name="gVersion_b" value="<%=obj1.GetGameVersion()%>"></b>
</td>
</tr>

<tr width="100%">
<td width="10%">
<b>Alpha     :</b>
</td>
<td width="90%" align="left">
<b><%=obj1.GetAlpha()%>
<input type= "hidden" name="alpha_b" value="<%=obj1.GetAlpha()%>"></b>
</td>
</tr>

<tr width="100%">
<td width="10%">
<b>Numbers per draw     :</b>
</td>
<td width="90%" align="left">
<b><%=obj1.GetNumbersPerDraw()%>
<input type= "hidden" name="numbersPerDraw_b" value="<%=obj1.GetNumbersPerDraw()%>"></b>
</td>
</tr>

</table>
<br>

<table width="100%" border="0">
<tr>
<td width="50%">
<table width="100%" border="0">

<%if(request.getAttribute("numberType") != null && request.getAttribute("numberType").equals("replacement"))
	{
%>
		<input type= "hidden" name="isReplacement" value="selected"> 
<%
	}
	else if(request.getAttribute("numberType") != null && request.getAttribute("numberType").equals("wc_replacement"))
	{
%>
		<input type= "hidden" name="wc_replacement" value="selected">
<%
	}
	else if(request.getAttribute("numberType") != null && request.getAttribute("numberType").equals("shuffled"))
	{
%>
		<input type= "hidden" name="shuffled" value="selected">
<%
	}
	if(request.getAttribute("selectAl")!= null)
	{
%>		<input type= "hidden" name="selectAll_b" value="selected">
<%	}


	String[] testNames = obj1.GetTestNames();
	//(String [])request.getAttribute("testName");
	String currentTest = "";
	for(int i=0; i < testNames.length ; i++)
	{
		if(testNames[i] != null)
		{
			currentTest = testNames[i];
			switch(currentTest)
			{
			case "cst" : %><tr><input type= "hidden" name="isChi" value="checked"><td width="50%"><%@ include file="chiSquareTestResult.jsp"%>
			</td>
</tr>
			<%
			break;
			case "rurdt" : %><tr><input type= "hidden" name="isRuns" value="checked"><td width="50%"><%@ include file="runsUpRunsDownTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "ft" : %><tr><input type= "hidden" name="isFre" value="checked"><td width="50%"><%@ include file="frequencyTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "kst" : %><tr><input type= "hidden" name="isKst" value="checked"><td width="50%"><%@ include file="ksTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "sct" : %><tr><input type= "hidden" name="isSct" value="checked"><td width="50%"><%@ include file="serialCorrelationTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "ict" : %><tr><input type= "hidden" name="isIct" value="checked"><td width="50%"><%@ include file="interplayCorrelationTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "mrt" : %><tr><input type= "hidden" name="isMrt" value="checked"><td width="50%"><%@ include file="medianRunsTestResult.jsp"%>
			</td>
</tr><%
			break; 
			case "gt" : %><tr><input type= "hidden" name="isGap" value="checked"><td width="50%"><%@ include file="gapTestResult.jsp"%>
			</td>
</tr><%
			break; 
			case "st" : %><tr><input type= "hidden" name="isSt" value="checked"><td width="50%"><%@ include file="serialTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "cct" : %><tr><input type= "hidden" name="isCct" value="checked"><td width="50%"><%@ include file="couponCollectorTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "pt" : %><tr><input type= "hidden" name="isPt" value="checked"><td width="50%"><%@ include file="pokerTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "pert" : %><tr><input type= "hidden" name="isPert" value="checked"><td width="50%"><%@ include file="permutationTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "mt" : %><tr><input type= "hidden" name="isMt" value="checked"><td width="50%"><%@ include file="maximumOfTTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "ct" : %><tr><input type= "hidden" name="isCt" value="checked"><td width="50%"><%@ include file="collisionTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "ts" : %><tr><input type= "hidden" name="isTs" value="checked"><td width="50%"><%@ include file="subsequencesTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "dt" : %><tr><input type= "hidden" name="isDt" value="checked"><td width="50%"><%@ include file="duplicatesTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "pdt" : %><tr><input type= "hidden" name="isPdt" value="checked"><td width="50%"><%@ include file="poissonDistributionTestResult.jsp"%>
			</td>
</tr><%
			break;
			case "udt" : %><tr><input type= "hidden" name="isUdt" value="checked"><td width="50%"><%@ include file="adjacencyTestResult.jsp"%>
			</td>
</tr><%
			break;
			
		}
	}
}	
%>

</tr>
</table>
</td>
<td width="50%">
<%
	if (request.getParameter("exportToExcel") == null) 
	{
%>
		<%@ include file="graphResult.jsp"%>
<%	}
%>
</td>
</tr>
</table>
</td>

</tr>
   <%
        String exportToExcel = "";
		if(request.getParameter("exportToExcel")!= null)
		{
			exportToExcel = request.getParameter("exportToExcel");
		}
		
        if (exportToExcel != null && exportToExcel.toString().equalsIgnoreCase("YES")) 
		{
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=excel.xls");
        }
    %>
<tr>
<%
	if (request.getParameter("exportToExcel") == null) 
	{
%>
<td width="100%"><center>
 
  <a href="jsp/result.jsp?exportToExcel=YES" >Export to excel</a>
  <button type="submit" value="backSubmit" >Back</button>
  
</center>
</td>
</tr>
<%	}
%>
</table>
</body>
</html>
<%
	}
%>
</form>

