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

import com.nandhootoo.domain.Status;
import com.nandhootoo.services.StatusService;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator; 
public class StatusDisplayAction extends BaseAction implements ServletRequestAware {


	private String selectStatement ;

	public Status theStatus ;
	public StatusService statusService ;
	private boolean deleteButtonPressed = false ;
	private boolean editButtonPressed = false ;
	private boolean returnButtonPressed = false ;
	private Integer tableCount = 0 ;
	private Map displayButtonMap = new HashMap() ;
	private Map editButtonMap = new HashMap() ;
	private Integer index ;  



	/* ********************************************************************************************** 
	 * *    Spring-injected constructor                                                               
	 * **********************************************************************************************/

	public StatusDisplayAction (StatusService passedService)
	{	this.statusService = passedService ;
	}	

	public void setIndex(Integer passedValue)
	{	index = passedValue;
	}	

	public Integer gIndex()
	{	return index;
	}	

	/* ********************************************************************************************** 
	 * *    Buttons                                                                                   
	 * **********************************************************************************************/

	public void setEditButtonPressed (boolean aValue) 
	{	editButtonPressed = true ;
	}

	public void setReturnButtonPressed (boolean aValue) 
	{	returnButtonPressed = true ;
	}

	public void setDeleteButtonPressed (boolean aValue) 
	{	deleteButtonPressed = true ;
	}

	public String getSelectStatement()
	{	// so the List will show the last entered StatusSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("StatusQuery");
		return selectStatement ;
	}

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Status>) request.getSession(true).getAttribute("StatusList");  
		return listToDisplay ;  
	}  

	public String getTableCount() 
	{	// return the number of rows in the table 
		List<Status> listToDisplay =  (List<Status>) request.getSession(true).getAttribute("StatusList"); 
		tableCount = listToDisplay.size() ; 
		if (tableCount == 1) 
			return "1 row" ; 
		DecimalFormat df = new DecimalFormat() ; 
		df.applyPattern("#,##0"); 
		return df.format(tableCount) + " rows" ; 
	} 

	/* **********************************************************************************************
	 * *    Get the Status that the page will display                                            
	 * **********************************************************************************************/
	public Status  getTheStatus()  
	{	if (theStatus == null)  
			theStatus = this.getStatusFromSession() ;  
		return theStatus ;  
	}   

	private Status getStatusFromSession()
	{
		Status tempStatus=(Status)request.getSession(true).getAttribute("Status");
		return tempStatus;
	}

	/* **********************************************************************************************
	 * *    Execute                                                                                   
	 * **********************************************************************************************/
	
	public String execute() throws Exception
	{	
		if (editButtonPressed)
	    {	return "edit" ;
		}
		if (returnButtonPressed)
	    {	return "returnToList" ;
		}
		if (deleteButtonPressed)
		{	
		try	
		{	// selectedItems contains the statuses of the items that were checked.
			// delete all records in the selectedItems list.
			statusService.delete (theStatus);
			return "refresh" ;
		}
		catch (Exception err)
		{
			addActionError(err.getCause().getMessage() ) ;
		}
		return SUCCESS; 
		}
		return "refresh" ;
	}
}
