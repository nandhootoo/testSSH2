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

import com.nandhootoo.domain.Department;
import com.nandhootoo.services.DepartmentService;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator; 
public class DepartmentEditAction extends BaseAction implements ServletRequestAware {



	private String selectStatement ;

	public Department theDepartment ;
	private DepartmentService departmentService ;
	private Integer index ;
	private boolean returnButtonPressed = false ;
	private boolean saveButtonPressed = false ;

	/* ********************************************************************************************** 
	 * *    Spring-injected constructor      
	 * **********************************************************************************************/
	public DepartmentEditAction(DepartmentService passedService)
	{	this.departmentService = passedService ;
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
	{	// so the List will show the last entered DepartmentSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("DepartmentQuery");
		return selectStatement ;
	}
	/* ********************************************************************************************** 
	 * *    Other Required Stuff                                                                                   
	 * **********************************************************************************************/
	private void addNewObjectToList(Department theDepartment) 
	{	// Do this so the List will show the newly created object. 
		// Note, however, that if this object has an auto-generated key, 
		//   the key will not appear, since it hasn't been generated yet. 
		List listToDisplay = getListToDisplay(); 
		listToDisplay.add(theDepartment) ; 
		request.getSession(true).setAttribute("DepartmentList", listToDisplay);    
	} 

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Department>) request.getSession(true).getAttribute("DepartmentList");  
		return listToDisplay ;  
	}  

	public Integer getIndex() 
	{	return index ;
	}

	public void setIndex(Integer passedValue) 
	{	index = passedValue ;
	}

	/* **********************************************************************************************
	 * *    Get the Department object that the page will display                                            
	 * **********************************************************************************************/
	public Department  getTheDepartment()  
	{  	if (theDepartment == null)  
			theDepartment = this.getDepartmentFromSession() ;  
		return theDepartment ;  
	}   

	private Department getDepartmentFromSession()
	{
		Department tempDepartment=(Department)request.getSession(true).getAttribute("Department");
		return tempDepartment;
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
			id = getTheDepartment().getId() ;
		return id ;
	}

	private String name; 
	@RequiredStringValidator(message = "Name is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="24", message="Name cannot exceed ${maxLength} characters")
	public void setName (String name) 
	{	this.name = name ;
	}
	public String getName()
	{  	return name ;
	}

	private String description; 
	public void setDescription (String description) 
	{	this.description = description ;
	}
	public String getDescription()
	{  	return description ;
	}

	private Timestamp created; 
	@RequiredFieldValidator(message = "Please enter the created") 
	public void setCreated (Timestamp created) 
	{	this.created = created ;
	}
	public Timestamp getCreated()
	{  	return created ;
	}


	/* **********************************************************************************************
	 * *    Callbacks                                                                                   
	 * **********************************************************************************************/
	public String input()
	{
		theDepartment = (Department)request.getSession(true).getAttribute("Department") ;
		id = theDepartment.getId();
		name = theDepartment.getName();
		description = theDepartment.getDescription();
		created = theDepartment.getCreated();
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
			getTheDepartment() ;   
			if (theDepartment.hasNullKey() )  
				adding = true ;   
			// First check to see if anything actually changed
			if (!adding &&   (
			  equals(name, theDepartment.getName()  )
			&& equals(description, theDepartment.getDescription()  )
			&& equals(created, theDepartment.getCreated()  )  ) ) 
				return "returnToList" ;

			//  disallow changes to the primary key
			if (!adding
			&& ( !equals(getId(), theDepartment.getId() )) )
				{	addActionError("Cannot change the Primary Key. Delete the old record and add a new record."); 
					return "input" ; 
				} 

			// if we get here, that means we detected at least one change.  So apply all changes to the object,
			//    persist it, and then go back to the list app.
			StringBuffer errors = new StringBuffer() ;
			boolean firstError = true ;
			theDepartment.setName(name) ;
			theDepartment.setDescription(description) ;
			theDepartment.setCreated(created) ;
			if (hasActionErrors() ) 
			{	if (adding)
			 	{	Department department = new Department();
					request.getSession(true).setAttribute("Department", department) ;
				}
				return "input"; 
			}
			try
			{	if (adding) 
				{	departmentService.persist(theDepartment) ;
					this.addNewObjectToList(theDepartment) ;
				 }
				else
					departmentService.merge(theDepartment) ;
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
