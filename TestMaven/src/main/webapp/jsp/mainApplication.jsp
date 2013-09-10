<!DOCTYPE html PUBLIC

	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<%@taglib prefix="s" uri="/struts-tags" %>
	<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
	<title>User Display</title>
	<s:head />
	</head>
	<body>
	Main Application<br />
	<s:form action="mainApplication" theme="simple" >
	<br  /><font size="4">Select a table from the list:</font><br /><br />

		<s:submit name="DepartmentButtonPressed" value="Department" />
 	<br /> 
		<s:submit name="IssueButtonPressed" value="Issue" />
 	<br /> 
		<s:submit name="NoteButtonPressed" value="Note" />
 	<br /> 
		<s:submit name="ProductButtonPressed" value="Product" />
 	<br /> 
		<s:submit name="StatusButtonPressed" value="Status" />
 	<br /> 
		<s:submit name="UserButtonPressed" value="User" />
 	<br /> 
	</s:form>
	</body>
</html>