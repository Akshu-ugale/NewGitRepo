<table border="1" bgcolor="#E6E6FA">
<tr align="left" width="100%">
<td width="40%" ><top>
<cewolf:chart id="pieChart" title="Test Result" type="pie">
    <cewolf:gradientpaint>
        <cewolf:point x="0" y="0" color="#FFFFFF" />
        <cewolf:point x="300" y="0" color="#DDDDFF" />
    </cewolf:gradientpaint>
    <cewolf:data>
        <cewolf:producer id="pageViews" />
    </cewolf:data>
    <cewolf:chartpostprocessor id="seriesPaint">
        <cewolf:param name="0" value="#FFFFAA"/>
        <cewolf:param name="1" value="#AAFFAA"/>
        <cewolf:param name="2" value="#FFAAFF"/>
        
    </cewolf:chartpostprocessor>
    <cewolf:chartpostprocessor id="pieEnhancer">
        <cewolf:param name="interiorGap" value="0.05"/>
        <cewolf:param name="labelGap" value="0.05"/>
        <cewolf:param name="startAngle" value="90"/>
        <cewolf:param name="simpleLabels" value="false"/>
        <cewolf:param name="showSectionLabels" value="true"/>
        <cewolf:param name="showShadow" value="false"/>
        <cewolf:param name="explode_0.25" value="Pass"/>
        <cewolf:param name="explode_0.26" value="Fail"/>
    </cewolf:chartpostprocessor>
</cewolf:chart>
<cewolf:img chartid="pieChart" renderer="/cewolf" width="600" height="500"/>

</top>
</td>
</tr>

<tr width="100%">
<td width="100%" colspan="2">


</td>
</tr>
</table>


