<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page errorPage="errorPage.jsp" %> 
<jsp:useBean id="obj1" class="scaleddatamathstests.InputBaseData" scope="session"/>

<%
	String path1 = request.getContextPath();
	path1 = path1 + "/jsp/uploadInputFile.jsp";
%>

<form id="input" action="uploadInputFile" method="post" enctype="multipart/form-data">
<html>
<head>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE=JavaScript>
function OpenWindow()
{
	window.open("jsp/inputFileInformation.jsp","","toolbar=no,scrollbars=no,menubar=no,location=no,resizable=no,top=220,left=570,width=400,height=440"); 
}
function OpenHelp()
{
	window.open("jsp/numbersTypeInformation.jsp","","toolbar=no,scrollbars=yes,menubar=no,location=no,resizable=no,top=220,left=570,width=500,height=540"); 
}
function onLoad(event)
{
	var selectDropdown = document.forms["input"]["numberType"];
	selectDropdown.value = "replacement";
	disableTests(event);
}
function onLoadBack(event)
{
	var selectDropdown = document.forms["input"]["numberType"];
	disableTestsBack(event);
}
function disableTests(event)
{
	var selectDropdown = document.forms["input"]["numberType"].value; 
	var testname = document.forms["input"]["testName"];
	var length = testname.length;
	var selectAll1 = document.forms["input"]["selectAl"];
	selectAll1.checked = false;
	selectAll(event);
	if (selectDropdown == 'wc_replacement')
	{
        for(var i=0; i < length; i++)
		{
			
			if(testname[i].value == "pt" || testname[i].value == "cct" || testname[i].value == "mt" || 
			testname[i].value == "rurdt" || testname[i].value == "gt" || testname[i].value == "mrt" || 
			testname[i].value == "ts" || testname[i].value == "sct" || testname[i].value == "st" ||
			testname[i].value == "pdt" )
			{
				testname[i].disabled = true;
			}
			else
			{
				testname[i].disabled = false;
			}
		}
    }
	else if (selectDropdown == 'replacement')
	{
		for(var i=0; i < length; i++)
		{
			testname[i].disabled = false;
		}
	}
	else if (selectDropdown == 'shuffled')
	{
		for(var i=0; i < length; i++)
		{
			
			if(testname[i].value == "ict" || testname[i].value == "pert" || testname[i].value == "dt" || testname[i].value == "udt")
			{
				testname[i].disabled = false;
			}
			else
			{
				testname[i].disabled = true;
			}
		}
	}
}

function disableTestsBack(event)
{
	var selectDropdown = document.forms["input"]["numberType"].value; 
	var testname = document.forms["input"]["testName"];
	var length = testname.length;
	
	if (selectDropdown == 'wc_replacement')
	{
        for(var i=0; i < length; i++)
		{
			
			if(testname[i].value == "pt" || testname[i].value == "cct" || testname[i].value == "mt" || 
			testname[i].value == "rurdt" || testname[i].value == "gt" || testname[i].value == "mrt" || 
			testname[i].value == "ts" || testname[i].value == "sct" || testname[i].value == "st" ||
			testname[i].value == "pdt" )
			{
				testname[i].disabled = true;
			}
			else
			{
				testname[i].disabled = false;
			}
		}
    }
	else if (selectDropdown == 'replacement')
	{
		for(var i=0; i < length; i++)
		{
			testname[i].disabled = false;
		}
	}
	else if (selectDropdown == 'shuffled')
	{
		for(var i=0; i < length; i++)
		{
			
			if(testname[i].value == "ict" || testname[i].value == "pert" || testname[i].value == "dt" || testname[i].value == "udt")
			{
				testname[i].disabled = false;
			}
			else
			{
				testname[i].disabled = true;
			}
		}
	}
}

function selectAll(source) 
{
	var selectAll1 = document.forms["input"]["selectAl"];
	var testname = document.forms["input"]["testName"];
	var length = testname.length;
	if(selectAll1.checked)
	{
		for(var i=0; i < length; i++)
		{
			if(testname[i].disabled == false)
			testname[i].checked = true;
		}
	}
	else
	{
		for(var i=0; i < length; i++)
		{
			testname[i].checked = false;
		}
	}
	
}

function isNumber(evt) 
{
	var iKeyCode = (evt.which) ? evt.which : evt.keyCode
	if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
	{
		alert("Please enter only real numbers");
		return false;
	}
	return true;
}    

function manadatoryFields(event)
{
	var gname = document.forms["input"]["gName"].value; 
	var vname = document.forms["input"]["VName"].value;
	var alpha = document.forms["input"]["alpha"].value;
	var numbersPerDraw = document.forms["input"]["numbersPerDraw"].value;
	var file = document.forms["input"]["filenameupload"].files[0];
	var testname = document.forms["input"]["testName"];
	var length = testname.length;
	var counttest = 0;
	var selectDropdown = document.forms["input"]["numberType"].value;
	var isNumbertypeWCReplacement = "false";
	var isIctChecked = "false";
	// Check if atleast one check box is selected
	for(var i=0; i < length; i++)
	{
		if(!testname[i].checked)
		{
			counttest++;
		}
		else if(testname[i].value == 'ict')
		{
			isIctChecked = "true";
		}
		
	}
	
	// Check if numbertype is without replacement
	if (selectDropdown == 'wc_replacement')
	{
		isNumbertypeWCReplacement = "true";
	}
	
	// Check if game name is empty
	if (gname == "")  
	{  
		alert("Please enter value for Game Name");  
		input.gName.focus();
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		} 
		else 
		{
			event.preventDefault();
		}
				
	}
	
	// Check if game version is empty
	else if (vname == "")  
	{  
		alert("Please enter value for Game Version");  
		input.VName.focus();
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		}
		else 
		{
			event.preventDefault();
		}
	}
	
	// Check if alpha is empty
	else if (alpha == "")  
	{  
		alert("Please enter value for alpha");  
		input.alpha.focus();
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		} 
		else 
		{
			event.preventDefault();
		}
	}
	
	else if (alpha != 0.1 && alpha != 0.05 && alpha != 0.01)
	{
		alert("Level of alpha should be either 0.01 Or 0.05 Or 0.1");
		input.alpha.focus();
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		} 
		else 
		{
			event.preventDefault();
		}
	}
	
	// Check if file name is empty
	else if(file == undefined)
	{
		alert("Please upload input file with scaled data");  
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		} 
		else 
		{
			event.preventDefault();
		}
	}
	
	else if((numbersPerDraw == "" || numbersPerDraw == 0.0) && isNumbertypeWCReplacement == "true" &&  isIctChecked == "true")
	{
		alert("Please enter numbers per draw this field is manadatory for number type without replacement and interplay correlation test together");  
		input.numbersPerDraw.focus();
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		} 
		else
		{
			event.preventDefault();
		}
	}
	
	// Check if atleast one check box is selected
	else if(counttest == 18)
	{
		alert("Please enter atleast one test");
		
		if (window.event) 
		{
			window.event.returnValue = false;
			event.preventDefault();
		} 
		else
		{
			event.preventDefault();
		}
	}

}
</SCRIPT>
	
<%  String GameName = "";
	String GameVersion = "";
	String Alpha = "";
	String NumbersPerDraw = "";
	String isChi = "", isRuns="", isFre="", isKst="", isSct="", isIct="", isMrt="", isGap="", isSt="", isCct="", shuffledSelected="";
	String isPt="", isPert="", isMt="", isCt="", isTs="", isDt="", isPdt="", isUdt="", isBack="", wc_replacementSelected="", replacementSelected="";
	String isSelectAll = "";
	
if(request.getParameter("isReplacement") != null && request.getParameter("isReplacement").equals("selected"))
{
	replacementSelected = request.getParameter("isReplacement").toString();
	replacementSelected = "selected";
}
if(request.getParameter("wc_replacement") != null && request.getParameter("wc_replacement").equals("selected"))
{
	wc_replacementSelected = request.getParameter("wc_replacement").toString();
	wc_replacementSelected = wc_replacementSelected.trim();
}
if(request.getParameter("shuffled") != null && request.getParameter("shuffled").equals("selected"))
{
	shuffledSelected = request.getParameter("shuffled").toString();
	shuffledSelected = shuffledSelected.trim();
}
if(request.getParameter("gName_b") != null)
{
	GameName = request.getParameter("gName_b").toString();
	GameName = GameName.trim();
}
if(request.getParameter("gVersion_b") != null)
{
	GameVersion = request.getParameter("gVersion_b").toString();
	GameVersion = GameVersion.trim();
}
if(request.getParameter("alpha_b") != null)
{
	Alpha = request.getParameter("alpha_b").toString();
	Alpha = Alpha.trim();
}
if(request.getParameter("selectAll_b") != null)
{
	isSelectAll = "checked";
	
}
if(request.getParameter("isChi") != null)
{
	isChi = request.getParameter("isChi").toString();
}
if(request.getParameter("isRuns") != null)
{
	isRuns = request.getParameter("isRuns").toString();
}
 if(request.getParameter("isFre") != null)
{
	isFre = request.getParameter("isFre").toString();
} 
if(request.getParameter("isKst") != null)
{
	isKst = request.getParameter("isKst").toString();
}     
if(request.getParameter("isSct") != null)
{
	isSct = request.getParameter("isSct").toString();
} 
if(request.getParameter("isIct") != null)
{
	isIct = request.getParameter("isIct").toString();
} 
if(request.getParameter("isMrt") != null)
{
	isMrt = request.getParameter("isMrt").toString();
}
if(request.getParameter("isGap") != null)
{
	isGap = request.getParameter("isGap").toString();
}
if(request.getParameter("isSt") != null)
{
	isSt = request.getParameter("isSt").toString();
}
if(request.getParameter("isCct") != null)
{
	isCct = request.getParameter("isCct").toString();
}
if(request.getParameter("isPt") != null)
{
	isPt = request.getParameter("isPt").toString();
}
if(request.getParameter("isPert") != null)
{
	isPert = request.getParameter("isPert").toString();
}
if(request.getParameter("isMt") != null)
{
	isMt = request.getParameter("isMt").toString();
}
if(request.getParameter("isCt") != null)
{
	isCt = request.getParameter("isCt").toString();
}
if(request.getParameter("isTs") != null)
{
	isTs = request.getParameter("isTs").toString();
}
if(request.getParameter("isDt") != null)
{
	isDt = request.getParameter("isDt").toString();
}
if(request.getParameter("isPdt") != null)
{
	isPdt = request.getParameter("isPdt").toString();
}
if(request.getParameter("isUdt") != null)
{
	isUdt = request.getParameter("isUdt").toString();
}
if(request.getParameter("numbersPerDraw_b") != null)
{
	NumbersPerDraw = request.getParameter("numbersPerDraw_b").toString();
	NumbersPerDraw = NumbersPerDraw.trim();
}
if(request.getParameter("isBack") != null)
{
	isBack = request.getParameter("isBack").toString();
}
%>

<title>Demo for RNG Tesing by Gaming Testing Laboratory</title>
</head>
<%if(!isBack.equals("Y"))
{%>
<body bgcolor=white onload = "onLoad(event)">
<%}else
{%>
<body bgcolor=white onload = "onLoadBack(event)">
<%}%>
<table border="0" width ="100%" bgcolor="#E6E6FA" height="100%">
<tr>
<td width="25%">
<img src="images/maths.jpg" style="width:95%; height:95%;"/>
&nbsp;
</td>
<td width="45%">

<table border="0" width="100%">
<tr width="100%">
<td align=center width="20%">
<img src="images/tomcat.GIF" style="width: 60px; height: 48px;"/>
</td>
<td width="80%">
<h1>SQS's Gizmo for RNG</h1>
</td>
</tr>
<tr>
<td width="100%" colspan="2">
&nbsp;
</td>
</tr>
</table>
<table border="0" width="100%">
<tr width="100%">
<td colspan="2">
</td>
</tr>
<tr width="100%">
<td width="40%">
Enter name of the RNG*
</td>
<td width="70%">
<input type="text" name="gName" value="<%=GameName%>">
</td>
</tr>
<tr width="100%">
<td width="30%">
Enter version of the RNG*
</td>
<td width="70%">
<input type="text" name="VName" value="<%=GameVersion%>">
</td>
</tr>
<tr width="100%">
<td width="30%">
Enter desired level of alpha within 12
<br> (0.01 Or 0.05 Or 0.10:)*
</td>
<td width="70%">
<input type="text" name="alpha" value="<%=Alpha%>" onkeypress="javascript:return isNumber(event)">
</td>
</tr>
<tr width="100%">
<td width="30%">
Numbers type* 
</td>
<td width="70%">
 <select id="numberType" name="numberType" onchange="disableTests(event)">
  <option value="replacement" <%=replacementSelected%>>With replacement</option>
  <option value="wc_replacement" <%=wc_replacementSelected%>>Without replacement</option>
  <option value="shuffled" <%=shuffledSelected%>>Shuffled Number</option>
 </select> 
 <button type="button" value="open_help" onClick="OpenHelp()" title="Click here if you are not sure to select the numbers type">Help</button>
</td>
</tr>
<tr width="100%">
<td width="30%">
Enter Numbers per draw** 
</td>
<td width="70%">
<input type="text" name="numbersPerDraw" value="<%=NumbersPerDraw%>" onkeypress="javascript:return isNumber(event)">
</td>
</tr>
<tr width="100%">
<td width="30%">
Enter the file with scaled data*
</td>
<td width="70%">
 
<input type="file" name="filenameupload1" id="filenameupload" accept=".txt,.bat,.dat,.bin,.File,.xml">

</td>
</tr>
<tr>
<td colspan="2" width="100%">
<button type="button" value="open_window" onClick="OpenWindow()">Required Fileformat</button>
</td>
</tr>
</table>
<br><b>
Select the desired test*</b>
<br><br>
<table width="100%">
<tr width="100%">
<td width="43%">
  <input type="checkbox" name="testName" value="cst" <%=isChi%>> Chi-Square Test
  </td>
  <td width="60%">
  <input type="checkbox" name="testName" value="ft" <%=isFre%> > Equidistribution Test (Frequency Test)<br>
  </td>
  </tr>
  <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="rurdt" <%=isRuns%>> Runs Up and Runs Down Test
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="kst" <%=isKst%>>  The Kolmogorov-Smirnov Test<br>
  </td>
  </tr>
    <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="sct" <%=isSct%> > Serial Correlation Test
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="pt" <%=isPt%>> Poker Test (Partition Test) <br>
  </td>
  </tr>
    
  <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="ict" <%=isIct%> > Interplay Correlation Test
  </td>
  <td width="80%">
   <input type="checkbox" name="testName" value="dt" <%=isDt%>> Duplicates Test<br>
 
  </td>
  </tr>


    <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="st" <%=isSt%>> Serial Test
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="cct" <%=isCct%>> Coupon collector’s Test<br>
  </td>
  </tr>
  <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="gt" <%=isGap%>> Gap Test
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="pert" <%=isPert%>> Permutation Test<br>
  </td>
  </tr>
  <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="mt" <%=isMt%>> Maximum-of-t Test
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="ct" <%=isCt%> > Collision Test<br>
  </td>
  </tr>
  <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="ts" <%=isTs%>> Tests on subsequences
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="mrt" <%=isMrt%>> Median Runs Test<br>
  </td>
  </tr>
  <tr width="100%">
<td width="20%">
  <input type="checkbox" name="testName" value="pdt" <%=isPdt%>> Poisson Distribution Test
  </td>
  <td width="80%">
  <input type="checkbox" name="testName" value="udt" <%=isUdt%>> Adjacency Test<br>
  </td>
  </tr>
  
  <tr>
  <td colspan="2"> &nbsp;
  </td>
  </tr>
<!--deleted code for interplay correlation tests paramters-->
  <tr>
  <td colspan="2" width="100%">
  
<input type="checkbox" name="selectAl" value="checked" <%=isSelectAll%> onClick="selectAll(this)"> Select All

  <br>
  <br>
   &nbsp; Fields marked with * are mandatory. <br> &nbsp; Fields marked with ** are mandatory only for Number type "Without Replacement".

  <br>
   &nbsp;
  <br>
  <br>
  <br>
  <br>
   &nbsp;
  <br>
  <br>
<br>
  </td>
  </tr>
    <tr width="100%">
<td width="20%">
   &nbsp;
  </td>
  <td width="80%">
  <button type="submit" value="Submit" onClick="manadatoryFields(event)">Submit</button>
  </td>
  </tr>
  </table>

  </td>
  <td width="30%">
  <table border="0" width="100%" height="100%">
  <tr height="50%">
<td>
 <img src="images/level3.png" style="width: 380px; height:360;"/>
</td>
</tr>
  <tr height="50%">
  <td width="100%" >
  
  <marquee behavior="scroll" direction="up" height="80%">
    <b>
  1. All these tests are derived from Knuth's book - The Art of Computer Programming - Volume2 - Seminumerical Algorithms - Second Edition<br><br>
  2. The input file needs to be in decimal format - each decimal number on new line.<br><br>
  3. The sample file is as below:-<br><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;43<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;23<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;12<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;54<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;67<br><br>
  4. Level of significance(alpha) = It is the probability of rejecting the null hypothesis given that it is true<br><br>
  5. Acceptable level of significance = 0.1, 0.05 and 0.01<br><br>
  6. Degree of freedom = Number of symbols - 1</b></marquee>

&nbsp;
</td>
</tr>

</td>
</tr>  
</table>
</td>
</tr>
</table>
 <%
String tests[]= request.getParameterValues("testName");
//String tests1[]= request.getParameterValues("selectAl");
%>


</body>
</html>
</form>

