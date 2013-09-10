
<!DOCTYPE html PUBLIC  
"-//W3C//DTD XHTML 1.1 Transitional//EN"
http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<link href="/TestMaven/styles/balataOT.css" rel="stylesheet" type="text/css" media="all"/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>userEdit</title>
	<s:head />
</head>
<body>
<b>users Edit</b><br /><br />
<s:form action="userEdit" >
	<s:actionerror />
	<s:fielderror />
	<table class="balataOT" border="1" > 
	<tr><td class="right" ><b><i>id:</i></b></td><td><s:property value="id" />&nbsp; &nbsp(Primary Key)</td></tr>
	<s:textfield name="username" label="username" />
	<s:textfield name="firstname" label="firstname" />
	<s:textfield name="lastname" label="lastname" />
	<s:textfield name="department" label="department" />
	<s:textfield name="created" label="created" />
	</table> 
<br/>
<s:submit name="saveButtonPressed" value="Save" align="left" />
<s:submit name="cancelButtonPressed" value="Cancel" align="left" method="cancel" />
</s:form>
</body>
</html>