<table border="0" bgcolor="#E6E6FA">
<tr width="100%">
<td width="40%">
<b>Test Name</b>
</td>
<td width="75%">
<b>Serial Correlation Test</b>
</td>
</tr>

<tr width="100%">
<td width="25%">
Number of Samples
</td>
<td width="75%" align="left">
<%=obj1.GetNumberOfSamplesSC()%>
</td>
</tr>

<tr width="100%">
<td width="25%">
Correlation Coefficient
</td>
<td width="75%" align="left">
<%=String.format("%.15f",obj1.GetCorrelationCoefficient())%>
</td>
</tr>

<tr width="100%">
<td width="25%">
Result
</td>
<td width="75%">
<b><%=obj1.GetserialCorrResult()%></b>
</td>
</tr>

<tr width="100%">
<td width="100%" colspan="2">

<!--<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>	-->	
-------------------------------------------------------------------------------------------------------------------
</td>
</tr>
</table>