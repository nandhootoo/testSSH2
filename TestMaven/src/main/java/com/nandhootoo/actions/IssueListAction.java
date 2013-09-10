/* ********************************************************************************************* 
 *   - - - - - - - - S T R U T S 2 B U I L D E R - - - - - - - -
 *   This file was automatically generated on Tue Sep 10 00:39:54 CST 2013
 *   Struts2Builder 0.5.0 was released on August 28, 2012
 *   Internal Subversion Info:
 *		$Revision: 34 $
 *		$Date: 2012-08-27 11:17:52 -0400 (Mon, 27 Aug 2012) $
 *		$Author: tom $
 *   Struts2Builder is copyright(c) 2009-2012 by
 *       Balata Object Technology
 *       Farmingdale, New York, USA 11735
 *   For more information, please visit http://struts2builder.sourceforge.net
 *   Struts2Builder has been released under the MIT license
 *   You are free to use this software in any project, commercial or non-commercial
 * ********************************************************************************************/
package com.nandhootoo.actions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.nandhootoo.domain.Issue;
import com.nandhootoo.services.IssueService;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator; 
public class IssueListAction extends BaseAction implements ServletRequestAware {

	private Issue theIssue ;
	private IssueService issueService ;
	private boolean addNewButtonPressed = false ;
	private boolean deleteSelectedButtonPressed = false ;
	private boolean editButtonPressed = false ;
	private Map editButtonMap = new HashMap() ;
	private Integer index ;
	private boolean queryButtonPressed = false ;
	private boolean returnButtonPressed = false ;
	private Integer tableCount = 0 ;
	private String selectStatement ;

	List<Integer> selectedItems = new ArrayList() ;
	private List<Issue> listToDisplay = new ArrayList() ;


	/* ********************************************************************************************** 
	 * *    Spring-injected constructor                                                               
	 * **********************************************************************************************/
	public IssueListAction(IssueService passedService)
	{	this.issueService = passedService ;
	} 


	/* ********************************************************************************************** 
	 * *    Buttons                                                                                   
	 * **********************************************************************************************/
	public void setQueryButtonPressed (boolean aValue) 
	{	queryButtonPressed = true ;
	}


	public void setReturnButtonPressed (boolean aValue) 
	{	returnButtonPressed = true ;
	}
	public void setAddNewButtonPressed (boolean aValue) 
	{	addNewButtonPressed = true ;
	}

	public void setDeleteSelectedButtonPressed (boolean aValue) 
	{	deleteSelectedButtonPressed = true ;
	}

	public void setSelectChecked (String[] theStrings)
	{
		theStrings.toString() ;
	}

	public void setSelectedItems(Integer[] passedSelectedItems)
	{	selectedItems = new ArrayList(Arrays.asList(passedSelectedItems)) ;
	}
	public List getListToDisplay()
	{	listToDisplay =  (List<Issue>) request.getSession(true).getAttribute("IssueList"); 
		return listToDisplay ;
	}

	public void setSelectStatement(String val)
	{	selectStatement = val ;
	    request.getSession(true).setAttribute("IssueQuery", selectStatement) ;
	}

	public String getSelectStatement()
	{	String selectStatement = (String)request.getSession(true).getAttribute("IssueQuery"); 
		return selectStatement ; 
	}

	public String getTableCount()
	{	// return the number of rows in the table
		if (tableCount == 1)
			return "1 row" ;
		DecimalFormat df = new DecimalFormat() ;
		df.applyPattern("#,##0");
		return df.format(tableCount) + " rows" ;
	}

	public void setIndex(Integer passedValue)
	{	index = passedValue ;
	}


	/* **********************************************************************************************
	 * *    Database Access                                                                          
	 * **********************************************************************************************/
	public List getIssues()
	{	// Notify the service to issue the query; catch any errors here, and display them in the errorField
		try
		{	List<Issue> theList = issueService.getIssuesWithCriteria(selectStatement) ;
			request.getSession(true).setAttribute("IssueList", theList) ; 
			tableCount = theList.size() ;
			return theList ;
		}
		catch (Exception err)
		{
			addActionError(err.getCause().getMessage() ) ;
		}
		return null ;
	}


	/* **********************************************************************************************
	 * *    Get the Issue that the display or edit page will display   
	 * **********************************************************************************************/
	public Issue getTheIssue()  
	{  	if (theIssue == null)  
			theIssue = this.getIssueFromSession() ;  
		return theIssue ;  
	}   

	private Issue getIssueFromSession()
	{
		Issue tempIssue=(Issue)request.getSession(true).getAttribute("Issue");
		return tempIssue;
	}

	/* **********************************************************************************************
	* * Callback methods from the individual links within the table.
	 * **********************************************************************************************/

	public String display() 
	{	// Get the Issue record, stuff it into the session object, then invoke the Displayer 
		List<Issue> theList = (List)request.getSession(true).getAttribute("IssueList") ;  
		Issue theIssue = theList.get(index) ;
		request.getSession(true).setAttribute("Issue", theIssue); 
		return "displaySingle" ; 
	} 

	public String edit() 
	{	// Get the Issue record, stuff it into the session object, then invoke the Displayer 
		List<Issue> theList = (List)request.getSession(true).getAttribute("IssueList") ;  
		Issue theIssue = theList.get(index) ;
		request.getSession(true).setAttribute("Issue", theIssue); 
		return "editSingle" ; 
	} 

	public String remove() 		
	{	try
		{	listToDisplay = (List)request.getSession(true).getAttribute("IssueList") ;  
			Issue theIssue = listToDisplay.get(index) ;
			issueService.delete(theIssue); 
			listToDisplay.remove(theIssue); 
			request.getSession(true).setAttribute("IssueList", listToDisplay); 
			tableCount = listToDisplay.size(); 
		}
		catch (Exception err)
		{	addActionError(err.getCause().getMessage() ) ;
		}
		return SUCCESS; 
	} 

	/* **********************************************************************************************
	* *    Execute                                                                                   
	 * **********************************************************************************************/
	
	public String execute() throws Exception
	{
		if (deleteSelectedButtonPressed)
		{	// selectedItems contains the indices of the items that were checked. 		
			// delete all records in the selectedItems list. 		
			List<Issue> theList = (List)request.getSession(true).getAttribute("IssueList") ;  
			try { issueService.deleteItems(selectedItems, theList ); 		
			}  
			catch (Exception err)  
			{ 	addActionError(err.getCause().getMessage() ) ; 
				return "refresh" ; 
			}  
			// Now spin through the saved list, find the Issues we deleted,   		
			//     and just remove them from the list.   		
			List<Issue> copiedList = new ArrayList() ; 
			copiedList.addAll(theList) ; 
			for (Integer each : selectedItems) 		
			{	Issue aIssue = copiedList.get(each) ; 
				theList.remove(aIssue) ; 		
			} 		
			request.getSession(true).setAttribute("IssueList", theList); 		 		
			tableCount = theList.size() ; 		 		
			return "refresh" ; 		
		}
		if (queryButtonPressed)
		{	
			listToDisplay = this.getIssues()  ;
			return "refresh" ;
		}
		if (addNewButtonPressed)
		{	// Create an empty Issue object, stuff it into the session, and invoke the Editor on it
			Issue issue = new Issue();
	    	request.getSession(true).setAttribute("Issue", issue) ;
			return "editSingle" ;
		}
		if (returnButtonPressed)
		{	// If return was hit, remove the list of items from the session.  Save some memory this way. 
			request.getSession(true).removeAttribute("IssueList"); 
			return "returnToMainApp" ;
		}
		return "refresh" ;
	}
}
