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
import com.nandhootoo.domain.Product ;
import com.nandhootoo.services.ProductService ;
import com.nandhootoo.domain.Status ;
import com.nandhootoo.services.StatusService ;
import com.nandhootoo.domain.User ;
import com.nandhootoo.services.UserService ;
public class IssueEditAction extends BaseAction implements ServletRequestAware {



	private String selectStatement ;

	public Issue theIssue ;
	private IssueService issueService ;
	private Integer index ;
	private ProductService productService ;
	private StatusService statusService ;
	private UserService userService ;
	private boolean returnButtonPressed = false ;
	private boolean saveButtonPressed = false ;

	/* ********************************************************************************************** 
	 * *    Spring-injected constructor      
	 * **********************************************************************************************/
	public IssueEditAction(IssueService passedService)
	{	this.issueService = passedService ;
	} 

	/* ********************************************************************************************** 
	 * *    Spring-injected Services for each Imported Foreign Key  
	 * **********************************************************************************************/
	public void setProductService (ProductService passedService)
	{	productService = passedService ; 
	} 
	public void setStatusService (StatusService passedService)
	{	statusService = passedService ; 
	} 
	public void setUserService (UserService passedService)
	{	userService = passedService ; 
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
	{	// so the List will show the last entered IssueSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("IssueQuery");
		return selectStatement ;
	}
	/* ********************************************************************************************** 
	 * *    Other Required Stuff                                                                                   
	 * **********************************************************************************************/
	private void addNewObjectToList(Issue theIssue) 
	{	// Do this so the List will show the newly created object. 
		// Note, however, that if this object has an auto-generated key, 
		//   the key will not appear, since it hasn't been generated yet. 
		List listToDisplay = getListToDisplay(); 
		listToDisplay.add(theIssue) ; 
		request.getSession(true).setAttribute("IssueList", listToDisplay);    
	} 

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Issue>) request.getSession(true).getAttribute("IssueList");  
		return listToDisplay ;  
	}  

	public Integer getIndex() 
	{	return index ;
	}

	public void setIndex(Integer passedValue) 
	{	index = passedValue ;
	}

	/* **********************************************************************************************
	 * *    Get the Issue object that the page will display                                            
	 * **********************************************************************************************/
	public Issue  getTheIssue()  
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
	 * *    Fields                                                                           
	 * **********************************************************************************************/
	private Integer id; 
	public void setId (Integer id) 
	{	this.id = id ;
	}
	public Integer getId()
	{  	if (id == null)		
			id = getTheIssue().getId() ;
		return id ;
	}

	private Integer product; 
	@RequiredFieldValidator(message = "Please enter the product") 
	public void setProduct (Integer product) 
	{	this.product = product ;
	}
	public Integer getProduct()
	{  	return product ;
	}

	private String version; 
	@RequiredStringValidator(message = "Version is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="15", message="Version cannot exceed ${maxLength} characters")
	public void setVersion (String version) 
	{	this.version = version ;
	}
	public String getVersion()
	{  	return version ;
	}

	private Integer status; 
	@RequiredFieldValidator(message = "Please enter the status") 
	public void setStatus (Integer status) 
	{	this.status = status ;
	}
	public Integer getStatus()
	{  	return status ;
	}

	private String description; 
	@RequiredStringValidator(message = "Description is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="255", message="Description cannot exceed ${maxLength} characters")
	public void setDescription (String description) 
	{	this.description = description ;
	}
	public String getDescription()
	{  	return description ;
	}

	private Integer creator; 
	@RequiredFieldValidator(message = "Please enter the creator") 
	public void setCreator (Integer creator) 
	{	this.creator = creator ;
	}
	public Integer getCreator()
	{  	return creator ;
	}

	private Integer owner; 
	@RequiredFieldValidator(message = "Please enter the owner") 
	public void setOwner (Integer owner) 
	{	this.owner = owner ;
	}
	public Integer getOwner()
	{  	return owner ;
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
		theIssue = (Issue)request.getSession(true).getAttribute("Issue") ;
		id = theIssue.getId();
		product = theIssue.getProduct();
		version = theIssue.getVersion();
		status = theIssue.getStatus();
		description = theIssue.getDescription();
		creator = theIssue.getCreator();
		owner = theIssue.getOwner();
		created = theIssue.getCreated();
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
			getTheIssue() ;   
			if (theIssue.hasNullKey() )  
				adding = true ;   
			// First check to see if anything actually changed
			if (!adding &&   (
			  equals(product, theIssue.getProduct()  )
			&& equals(version, theIssue.getVersion()  )
			&& equals(status, theIssue.getStatus()  )
			&& equals(description, theIssue.getDescription()  )
			&& equals(creator, theIssue.getCreator()  )
			&& equals(owner, theIssue.getOwner()  )
			&& equals(created, theIssue.getCreated()  )  ) ) 
				return "returnToList" ;

			//  disallow changes to the primary key
			if (!adding
			&& ( !equals(getId(), theIssue.getId() )) )
				{	addActionError("Cannot change the Primary Key. Delete the old record and add a new record."); 
					return "input" ; 
				} 

			// if we get here, that means we detected at least one change.  So apply all changes to the object,
			//    persist it, and then go back to the list app.
			StringBuffer errors = new StringBuffer() ;
			boolean firstError = true ;
			theIssue.setProduct(product) ;
			theIssue.setVersion(version) ;
			theIssue.setStatus(status) ;
			theIssue.setDescription(description) ;
			theIssue.setCreator(creator) ;
			theIssue.setOwner(owner) ;
			theIssue.setCreated(created) ;
			if (hasActionErrors() ) 
			{	if (adding)
			 	{	Issue issue = new Issue();
					request.getSession(true).setAttribute("Issue", issue) ;
				}
				return "input"; 
			}
			try
			{	if (adding) 
				{	issueService.persist(theIssue) ;
					this.addNewObjectToList(theIssue) ;
				 }
				else
					issueService.merge(theIssue) ;
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
