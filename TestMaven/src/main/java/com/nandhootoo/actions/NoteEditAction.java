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

import com.nandhootoo.domain.Note;
import com.nandhootoo.services.NoteService;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator; 
import com.nandhootoo.domain.Issue ;
import com.nandhootoo.services.IssueService ;
import com.nandhootoo.domain.User ;
import com.nandhootoo.services.UserService ;
public class NoteEditAction extends BaseAction implements ServletRequestAware {



	private String selectStatement ;

	public Note theNote ;
	private NoteService noteService ;
	private Integer index ;
	private IssueService issueService ;
	private UserService userService ;
	private boolean returnButtonPressed = false ;
	private boolean saveButtonPressed = false ;

	/* ********************************************************************************************** 
	 * *    Spring-injected constructor      
	 * **********************************************************************************************/
	public NoteEditAction(NoteService passedService)
	{	this.noteService = passedService ;
	} 

	/* ********************************************************************************************** 
	 * *    Spring-injected Services for each Imported Foreign Key  
	 * **********************************************************************************************/
	public void setIssueService (IssueService passedService)
	{	issueService = passedService ; 
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
	{	// so the List will show the last entered NoteSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("NoteQuery");
		return selectStatement ;
	}
	/* ********************************************************************************************** 
	 * *    Other Required Stuff                                                                                   
	 * **********************************************************************************************/
	private void addNewObjectToList(Note theNote) 
	{	// Do this so the List will show the newly created object. 
		// Note, however, that if this object has an auto-generated key, 
		//   the key will not appear, since it hasn't been generated yet. 
		List listToDisplay = getListToDisplay(); 
		listToDisplay.add(theNote) ; 
		request.getSession(true).setAttribute("NoteList", listToDisplay);    
	} 

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Note>) request.getSession(true).getAttribute("NoteList");  
		return listToDisplay ;  
	}  

	public Integer getIndex() 
	{	return index ;
	}

	public void setIndex(Integer passedValue) 
	{	index = passedValue ;
	}

	/* **********************************************************************************************
	 * *    Get the Note object that the page will display                                            
	 * **********************************************************************************************/
	public Note  getTheNote()  
	{  	if (theNote == null)  
			theNote = this.getNoteFromSession() ;  
		return theNote ;  
	}   

	private Note getNoteFromSession()
	{
		Note tempNote=(Note)request.getSession(true).getAttribute("Note");
		return tempNote;
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
			id = getTheNote().getId() ;
		return id ;
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

	private Integer issue; 
	@RequiredFieldValidator(message = "Please enter the issue") 
	public void setIssue (Integer issue) 
	{	this.issue = issue ;
	}
	public Integer getIssue()
	{  	return issue ;
	}

	private Integer creator; 
	@RequiredFieldValidator(message = "Please enter the creator") 
	public void setCreator (Integer creator) 
	{	this.creator = creator ;
	}
	public Integer getCreator()
	{  	return creator ;
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
		theNote = (Note)request.getSession(true).getAttribute("Note") ;
		id = theNote.getId();
		description = theNote.getDescription();
		issue = theNote.getIssue();
		creator = theNote.getCreator();
		created = theNote.getCreated();
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
			getTheNote() ;   
			if (theNote.hasNullKey() )  
				adding = true ;   
			// First check to see if anything actually changed
			if (!adding &&   (
			  equals(description, theNote.getDescription()  )
			&& equals(issue, theNote.getIssue()  )
			&& equals(creator, theNote.getCreator()  )
			&& equals(created, theNote.getCreated()  )  ) ) 
				return "returnToList" ;

			//  disallow changes to the primary key
			if (!adding
			&& ( !equals(getId(), theNote.getId() )) )
				{	addActionError("Cannot change the Primary Key. Delete the old record and add a new record."); 
					return "input" ; 
				} 

			// if we get here, that means we detected at least one change.  So apply all changes to the object,
			//    persist it, and then go back to the list app.
			StringBuffer errors = new StringBuffer() ;
			boolean firstError = true ;
			theNote.setDescription(description) ;
			theNote.setIssue(issue) ;
			theNote.setCreator(creator) ;
			theNote.setCreated(created) ;
			if (hasActionErrors() ) 
			{	if (adding)
			 	{	Note note = new Note();
					request.getSession(true).setAttribute("Note", note) ;
				}
				return "input"; 
			}
			try
			{	if (adding) 
				{	noteService.persist(theNote) ;
					this.addNewObjectToList(theNote) ;
				 }
				else
					noteService.merge(theNote) ;
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
