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

import com.nandhootoo.domain.User;
import com.nandhootoo.services.UserService;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator; 
import com.nandhootoo.domain.Department ;
import com.nandhootoo.services.DepartmentService ;
public class UserEditAction extends BaseAction implements ServletRequestAware {



	private String selectStatement ;

	public User theUser ;
	private UserService userService ;
	private Integer index ;
	private DepartmentService departmentService ;
	private boolean returnButtonPressed = false ;
	private boolean saveButtonPressed = false ;

	/* ********************************************************************************************** 
	 * *    Spring-injected constructor      
	 * **********************************************************************************************/
	public UserEditAction(UserService passedService)
	{	this.userService = passedService ;
	} 

	/* ********************************************************************************************** 
	 * *    Spring-injected Services for each Imported Foreign Key  
	 * **********************************************************************************************/
	public void setDepartmentService (DepartmentService passedService)
	{	departmentService = passedService ; 
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
	{	// so the List will show the last entered UserSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("UserQuery");
		return selectStatement ;
	}
	/* ********************************************************************************************** 
	 * *    Other Required Stuff                                                                                   
	 * **********************************************************************************************/
	private void addNewObjectToList(User theUser) 
	{	// Do this so the List will show the newly created object. 
		// Note, however, that if this object has an auto-generated key, 
		//   the key will not appear, since it hasn't been generated yet. 
		List listToDisplay = getListToDisplay(); 
		listToDisplay.add(theUser) ; 
		request.getSession(true).setAttribute("UserList", listToDisplay);    
	} 

	public List getListToDisplay() 
	{	List listToDisplay =  (List<User>) request.getSession(true).getAttribute("UserList");  
		return listToDisplay ;  
	}  

	public Integer getIndex() 
	{	return index ;
	}

	public void setIndex(Integer passedValue) 
	{	index = passedValue ;
	}

	/* **********************************************************************************************
	 * *    Get the User object that the page will display                                            
	 * **********************************************************************************************/
	public User  getTheUser()  
	{  	if (theUser == null)  
			theUser = this.getUserFromSession() ;  
		return theUser ;  
	}   

	private User getUserFromSession()
	{
		User tempUser=(User)request.getSession(true).getAttribute("User");
		return tempUser;
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
			id = getTheUser().getId() ;
		return id ;
	}

	private String username; 
	@RequiredStringValidator(message = "Username is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="15", message="Username cannot exceed ${maxLength} characters")
	public void setUsername (String username) 
	{	this.username = username ;
	}
	public String getUsername()
	{  	return username ;
	}

	private String firstname; 
	@RequiredStringValidator(message = "Firstname is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="24", message="Firstname cannot exceed ${maxLength} characters")
	public void setFirstname (String firstname) 
	{	this.firstname = firstname ;
	}
	public String getFirstname()
	{  	return firstname ;
	}

	private String lastname; 
	@RequiredStringValidator(message = "Lastname is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="24", message="Lastname cannot exceed ${maxLength} characters")
	public void setLastname (String lastname) 
	{	this.lastname = lastname ;
	}
	public String getLastname()
	{  	return lastname ;
	}

	private Integer department; 
	@RequiredFieldValidator(message = "Please enter the department") 
	public void setDepartment (Integer department) 
	{	this.department = department ;
	}
	public Integer getDepartment()
	{  	return department ;
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
		theUser = (User)request.getSession(true).getAttribute("User") ;
		id = theUser.getId();
		username = theUser.getUsername();
		firstname = theUser.getFirstname();
		lastname = theUser.getLastname();
		department = theUser.getDepartment();
		created = theUser.getCreated();
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
			getTheUser() ;   
			if (theUser.hasNullKey() )  
				adding = true ;   
			// First check to see if anything actually changed
			if (!adding &&   (
			  equals(username, theUser.getUsername()  )
			&& equals(firstname, theUser.getFirstname()  )
			&& equals(lastname, theUser.getLastname()  )
			&& equals(department, theUser.getDepartment()  )
			&& equals(created, theUser.getCreated()  )  ) ) 
				return "returnToList" ;

			//  disallow changes to the primary key
			if (!adding
			&& ( !equals(getId(), theUser.getId() )) )
				{	addActionError("Cannot change the Primary Key. Delete the old record and add a new record."); 
					return "input" ; 
				} 

			// if we get here, that means we detected at least one change.  So apply all changes to the object,
			//    persist it, and then go back to the list app.
			StringBuffer errors = new StringBuffer() ;
			boolean firstError = true ;
			theUser.setUsername(username) ;
			theUser.setFirstname(firstname) ;
			theUser.setLastname(lastname) ;
			theUser.setDepartment(department) ;
			theUser.setCreated(created) ;
			if (hasActionErrors() ) 
			{	if (adding)
			 	{	User user = new User();
					request.getSession(true).setAttribute("User", user) ;
				}
				return "input"; 
			}
			try
			{	if (adding) 
				{	userService.persist(theUser) ;
					this.addNewObjectToList(theUser) ;
				 }
				else
					userService.merge(theUser) ;
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
