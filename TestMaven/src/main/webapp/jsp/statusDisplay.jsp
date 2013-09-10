
<!DOCTYPE html PUBLIC  
"-//W3C//DTD XHTML 1.1 Transitional//EN"
http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<link href="/TestMaven/styles/balataOT.css" rel="stylesheet" type="text/css" media="all"/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>statusDisplay</title>
	<s:head />
</head>
<body>
<b>statuses Display</b><br /><br />
<s:form action="statusDisplay" theme="simple" >

<s:submit name="editButtonPressed" value="Edit" />
<s:submit name="returnButtonPressed" value="Return" />
<table class="balataOT" border="1" cellpadding="1" cellspacing="0" >
		<tr><th>#</th><th>Field</th><th>Value</th></tr>
		<tr><td class="right">1</td><td class="right" >id</td><td><s:property value="theStatus.id" /></td></tr>
		<tr><td class="right">2</td><td class="right" >description</td><td><s:property value="theStatus.description" /></td></tr>
	</table> 
<br/>
<s:submit name="editButtonPressed" value="Edit" />
<s:submit name="returnButtonPressed" value="Return" />
</s:form>
</body>
</html>