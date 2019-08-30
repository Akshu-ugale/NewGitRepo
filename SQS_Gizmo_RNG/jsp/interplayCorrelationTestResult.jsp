<table border="0" bgcolor="#E6E6FA">
<tr width="100%">
<td width="40%">
<b>Test Name</b>
</td>
<td width="60%">
<b>Interplay Correlation Test</b>
</td>
</tr>

<tr width="100%">
<td width="25%">
Average value of r
</td>
<td width="75%" align="left">
<%=String.format("%.15f",obj1.GetPassRate())%>
</td>
</tr>

<tr width="100%">
<td width="25%">
Graph Plot
</td>
<td width="75%" align="left">
<%=obj1.GetFailRate()%>
</td>
</tr>

<tr width="100%">
<td width="25%">
Result
</td>
<td width="75%">
<b><%=obj1.GetInterplayCorrResult()%></b>
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