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
public class StatusEditAction extends BaseAction implements ServletRequestAware {



	private String selectStatement ;

	public Status theStatus ;
	private StatusService statusService ;
	private Integer index ;
	private boolean returnButtonPressed = false ;
	private boolean saveButtonPressed = false ;

	/* ********************************************************************************************** 
	 * *    Spring-injected constructor      
	 * **********************************************************************************************/
	public StatusEditAction(StatusService passedService)
	{	this.statusService = passedService ;
	} 


	/* ********************************************************************************************** 
	 * *    Buttons                                                                                   
	 * **********************************************************************************************/
	public void setSaveButtonPressed (boolean aValue) 
	{	saveButtonPressed = true ;
	}

	public void setReturnButtonPressed (boolean aValue) 
	{	returnButtonPressed = true ;
	}

	public String getSelectStatement()
	{	// so the List will show the last entered StatusSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("StatusQuery");
		return selectStatement ;
	}
	/* ********************************************************************************************** 
	 * *    Other Required Stuff                                                                                   
	 * **********************************************************************************************/
	private void addNewObjectToList(Status theStatus) 
	{	// Do this so the List will show the newly created object. 
		// Note, however, that if this object has an auto-generated key, 
		//   the key will not appear, since it hasn't been generated yet. 
		List listToDisplay = getListToDisplay(); 
		listToDisplay.add(theStatus) ; 
		request.getSession(true).setAttribute("StatusList", listToDisplay);    
	} 

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Status>) request.getSession(true).getAttribute("StatusList");  
		return listToDisplay ;  
	}  

	public Integer getIndex() 
	{	return index ;
	}

	public void setIndex(Integer passedValue) 
	{	index = passedValue ;
	}

	/* **********************************************************************************************
	 * *    Get the Status object that the page will display                                            
	 * **********************************************************************************************/
	public Status  getTheStatus()  
	{  	if (theStatus == null)  
			theStatus = this.getStatusFromSession() ;  
		return theStatus ;  
	}   

	private Status getStatusFromSession()
	{
		Status tempStatus=(Status)request.getSession(true).getAttribute("Status");
		return tempStatus;
	}

	/* **********************************************************************************************
	 * *    Fields                                                                           
	 * **********************************************************************************************/
	private Integer id; 
	public void setId (Integer id) 
	{	this.id = id ;
	}
	public Integer getId()
	{  	if (id == null)		
			id = getTheStatus().getId() ;
		return id ;
	}

	private String description; 
	public void setDescription (String description) 
	{	this.description = description ;
	}
	public String getDescription()
	{  	return description ;
	}


	/* **********************************************************************************************
	 * *    Callbacks                                                                                   
	 * **********************************************************************************************/
	public String input()
	{
		theStatus = (Status)request.getSession(true).getAttribute("Status") ;
		id = theStatus.getId();
		description = theStatus.getDescription();
		return SUCCESS ;
	}

	public String cancel()
	{	return "returnToList" ; 
	}


	/* **********************************************************************************************
	 * *    Execute                                                                                   
	 * **********************************************************************************************/
	
	public String execute() throws Exception
	{	
		if (saveButtonPressed) 
		{	boolean adding = false ;   
			getTheStatus() ;   
			if (theStatus.hasNullKey() )  
				adding = true ;   
			// First check to see if anything actually changed
			if (!adding &&   (
			  equals(description, theStatus.getDescription()  )  ) ) 
				return "returnToList" ;

			//  disallow changes to the primary key
			if (!adding
			&& ( !equals(getId(), theStatus.getId() )) )
				{	addActionError("Cannot change the Primary Key. Delete the old record and add a new record."); 
					return "input" ; 
				} 

			// if we get here, that means we detected at least one change.  So apply all changes to the object,
			//    persist it, and then go back to the list app.
			StringBuffer errors = new StringBuffer() ;
			boolean firstError = true ;
			theStatus.setDescription(description) ;
			if (hasActionErrors() ) 
			{	if (adding)
			 	{	Status status = new Status();
					request.getSession(true).setAttribute("Status", status) ;
				}
				return "input"; 
			}
			try
			{	if (adding) 
				{	statusService.persist(theStatus) ;
					this.addNewObjectToList(theStatus) ;
				 }
				else
					statusService.merge(theStatus) ;
			} 
 			catch (Exception err) 
 			{	addActionError(err.getMessage() ) ; 
 				return "input" ; 
 			}
			return "returnToList" ;
		}
		if (returnButtonPressed)
			return "returnToList" ;
		return "input" ;
	}
}
