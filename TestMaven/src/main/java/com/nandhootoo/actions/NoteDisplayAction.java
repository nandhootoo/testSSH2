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
public class NoteDisplayAction extends BaseAction implements ServletRequestAware {


	private String selectStatement ;

	public Note theNote ;
	public NoteService noteService ;
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

	public NoteDisplayAction (NoteService passedService)
	{	this.noteService = passedService ;
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
	{	// so the List will show the last entered NoteSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("NoteQuery");
		return selectStatement ;
	}

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Note>) request.getSession(true).getAttribute("NoteList");  
		return listToDisplay ;  
	}  

	public String getTableCount() 
	{	// return the number of rows in the table 
		List<Note> listToDisplay =  (List<Note>) request.getSession(true).getAttribute("NoteList"); 
		tableCount = listToDisplay.size() ; 
		if (tableCount == 1) 
			return "1 row" ; 
		DecimalFormat df = new DecimalFormat() ; 
		df.applyPattern("#,##0"); 
		return df.format(tableCount) + " rows" ; 
	} 

	/* **********************************************************************************************
	 * *    Get the Note that the page will display                                            
	 * **********************************************************************************************/
	public Note  getTheNote()  
	{	if (theNote == null)  
			theNote = this.getNoteFromSession() ;  
		return theNote ;  
	}   

	private Note getNoteFromSession()
	{
		Note tempNote=(Note)request.getSession(true).getAttribute("Note");
		return tempNote;
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
		{	// selectedItems contains the notes of the items that were checked.
			// delete all records in the selectedItems list.
			noteService.delete (theNote);
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
