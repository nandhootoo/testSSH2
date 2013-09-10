
<!DOCTYPE html PUBLIC  
"-//W3C//DTD XHTML 1.1 Transitional//EN"
http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<link href="/TestMaven/styles/balataOT.css" rel="stylesheet" type="text/css" media="all"/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>userDisplay</title>
	<s:head />
</head>
<body>
<b>users Display</b><br /><br />
<s:form action="userDisplay" theme="simple" >

<s:submit name="editButtonPressed" value="Edit" />
<s:submit name="returnButtonPressed" value="Return" />
<table class="balataOT" border="1" cellpadding="1" cellspacing="0" >
		<tr><th>#</th><th>Field</th><th>Value</th></tr>
		<tr><td class="right">1</td><td class="right" >id</td><td><s:property value="theUser.id" /></td></tr>
		<tr><td class="right">2</td><td class="right" >username</td><td><s:property value="theUser.username" /></td></tr>
		<tr><td class="right">3</td><td class="right" >firstname</td><td><s:property value="theUser.firstname" /></td></tr>
		<tr><td class="right">4</td><td class="right" >lastname</td><td><s:property value="theUser.lastname" /></td></tr>
		<tr><td class="right">5</td><td class="right" >department</td><td><s:property value="theUser.department" /></td></tr>
		<tr><td class="right">6</td><td class="right" >created</td><td><s:property value="theUser.created" /></td></tr>
	</table> 
<br/>
<s:submit name="editButtonPressed" value="Edit" />
<s:submit name="returnButtonPressed" value="Return" />
</s:form>
</body>
</html>