
<!DOCTYPE html PUBLIC 
"-//W3C//DTD XHTML 1.1 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<link href="/TestMaven/styles/balataOT.css" rel="stylesheet" type="text/css" media="all"/>
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>
issueList</title>
<s:head />
</head>
<body>
<b>issues List</b><br /><br />
<s:form action="issueList" theme="simple"> 

<table><tr><td>select * from Issue</td><td><s:textfield name="selectStatement"  size="50" /></td></tr></table>
	<s:actionerror />
	<s:fielderror />
	<s:submit name="queryButtonPressed" value="Query" /> 
	<s:property value="tableCount" />
	<s:submit name="returnButtonPressed" value="Return" />
	<s:submit name="deleteSelectedButtonPressed" value="Delete Selected" /> 
	<s:submit name="addNewButtonPressed" value="Add New" /> 
	<br />
 
<table class="balataOT" border="1" cellpadding="1" cellspacing="0" width="100%">
		<tr>
		<th>#</th><th>Display</th><th>Edit</th><th>Remove</th><th>x</th>
		<th>id</th>
		<th>product</th>
		<th>version</th>
		<th>status</th>
		<th>description</th>
		<th>creator</th>
		<th>owner</th>
		</tr>

 		<s:iterator value="listToDisplay" id="listToDisplay" status="table_stat">
			<tr class="<s:if test="#table_stat.odd == true ">odd</s:if><s:else>even</s:else>">
		<td class="right"><s:property value="#table_stat.index + 1" /></td>
				<td class="center" >
					<s:url id="displayUrl" action="issueListActionDisplay"> 
						<s:param name="index" value = "#table_stat.index" /> 
					</s:url>
					<s:a href="%{displayUrl}" targets="issues">Display</s:a>
				</td> 
				<td class="center"> 
					<s:url id="editUrl" action="issueListActionEdit"> 
						<s:param name="index" value = "#table_stat.index" /> 
					</s:url>
					<s:a href="%{editUrl}" targets="issues">Edit</s:a>
				</td> 
				<td class="center"> 
					<s:url id="removeUrl" action="issueListActionRemove"> 
						<s:param name="index" value = "#table_stat.index" /> 
					</s:url>
					<s:a href="%{removeUrl}" targets="issues">Remove</s:a>
				</td> 
				<td ><input type="checkbox" name="selectedItems" value="<s:property value='#table_stat.index' />" /> </td> 
	
 				<td ><s:property value="id" /></td> 
 				<td ><s:property value="product" /></td> 
 				<td ><s:property value="version" /></td> 
 				<td ><s:property value="status" /></td> 
 				<td ><s:property value="description" /></td> 
 				<td ><s:property value="creator" /></td> 
 				<td ><s:property value="owner" /></td> 
     		</tr>
		</s:iterator>
	</table>
	<s:submit name="queryButtonPressed" value="Query" /> 
	<s:property value="tableCount" />
	<s:submit name="returnButtonPressed" value="Return" />
	<s:submit name="deleteSelectedButtonPressed" value="Delete Selected" /> 
	<s:submit name="addNewButtonPressed" value="Add New" /> 
	<br />
<br/>
</s:form>
</body>
</html>