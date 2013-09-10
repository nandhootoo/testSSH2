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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 

public class MainApplicationAction extends BaseAction implements ServletRequestAware {


	private boolean departmentButtonPressed = false ;
	private boolean issueButtonPressed = false ;
	private boolean noteButtonPressed = false ;
	private boolean productButtonPressed = false ;
	private boolean statusButtonPressed = false ;
	private boolean userButtonPressed = false ;


/* ********************************************************************************************** 
 * *    Buttons                                                                                   
 * **********************************************************************************************/

	public void setDepartmentButtonPressed (boolean aValue) 
	{	departmentButtonPressed = true ;
	}

	public void setIssueButtonPressed (boolean aValue) 
	{	issueButtonPressed = true ;
	}

	public void setNoteButtonPressed (boolean aValue) 
	{	noteButtonPressed = true ;
	}

	public void setProductButtonPressed (boolean aValue) 
	{	productButtonPressed = true ;
	}

	public void setStatusButtonPressed (boolean aValue) 
	{	statusButtonPressed = true ;
	}

	public void setUserButtonPressed (boolean aValue) 
	{	userButtonPressed = true ;
	}




/* **********************************************************************************************
 * *    Execute                                                                                   
 * **********************************************************************************************/
	
	public String execute() throws Exception
	{	
		if (departmentButtonPressed)
		{	return "department" ;
		}
		if (issueButtonPressed)
		{	return "issue" ;
		}
		if (noteButtonPressed)
		{	return "note" ;
		}
		if (productButtonPressed)
		{	return "product" ;
		}
		if (statusButtonPressed)
		{	return "status" ;
		}
		if (userButtonPressed)
		{	return "user" ;
		}

		return "refresh" ;
	}
}
