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

import com.nandhootoo.domain.Product;
import com.nandhootoo.services.ProductService;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator; 
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator; 
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator; 
public class ProductEditAction extends BaseAction implements ServletRequestAware {



	private String selectStatement ;

	public Product theProduct ;
	private ProductService productService ;
	private Integer index ;
	private boolean returnButtonPressed = false ;
	private boolean saveButtonPressed = false ;

	/* ********************************************************************************************** 
	 * *    Spring-injected constructor      
	 * **********************************************************************************************/
	public ProductEditAction(ProductService passedService)
	{	this.productService = passedService ;
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
	{	// so the List will show the last entered ProductSelect statement
		selectStatement = (String)request.getSession(false).getAttribute("ProductQuery");
		return selectStatement ;
	}
	/* ********************************************************************************************** 
	 * *    Other Required Stuff                                                                                   
	 * **********************************************************************************************/
	private void addNewObjectToList(Product theProduct) 
	{	// Do this so the List will show the newly created object. 
		// Note, however, that if this object has an auto-generated key, 
		//   the key will not appear, since it hasn't been generated yet. 
		List listToDisplay = getListToDisplay(); 
		listToDisplay.add(theProduct) ; 
		request.getSession(true).setAttribute("ProductList", listToDisplay);    
	} 

	public List getListToDisplay() 
	{	List listToDisplay =  (List<Product>) request.getSession(true).getAttribute("ProductList");  
		return listToDisplay ;  
	}  

	public Integer getIndex() 
	{	return index ;
	}

	public void setIndex(Integer passedValue) 
	{	index = passedValue ;
	}

	/* **********************************************************************************************
	 * *    Get the Product object that the page will display                                            
	 * **********************************************************************************************/
	public Product  getTheProduct()  
	{  	if (theProduct == null)  
			theProduct = this.getProductFromSession() ;  
		return theProduct ;  
	}   

	private Product getProductFromSession()
	{
		Product tempProduct=(Product)request.getSession(true).getAttribute("Product");
		return tempProduct;
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
			id = getTheProduct().getId() ;
		return id ;
	}

	private String description; 
	@RequiredStringValidator(message = "Description is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="24", message="Description cannot exceed ${maxLength} characters")
	public void setDescription (String description) 
	{	this.description = description ;
	}
	public String getDescription()
	{  	return description ;
	}

	private String currentVersion; 
	@RequiredStringValidator(message = "CurrentVersion is a required field", trim = true) 
	@StringLengthFieldValidator(maxLength="15", message="CurrentVersion cannot exceed ${maxLength} characters")
	public void setCurrentVersion (String currentVersion) 
	{	this.currentVersion = currentVersion ;
	}
	public String getCurrentVersion()
	{  	return currentVersion ;
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
		theProduct = (Product)request.getSession(true).getAttribute("Product") ;
		id = theProduct.getId();
		description = theProduct.getDescription();
		currentVersion = theProduct.getCurrentVersion();
		created = theProduct.getCreated();
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
			getTheProduct() ;   
			if (theProduct.hasNullKey() )  
				adding = true ;   
			// First check to see if anything actually changed
			if (!adding &&   (
			  equals(description, theProduct.getDescription()  )
			&& equals(currentVersion, theProduct.getCurrentVersion()  )
			&& equals(created, theProduct.getCreated()  )  ) ) 
				return "returnToList" ;

			//  disallow changes to the primary key
			if (!adding
			&& ( !equals(getId(), theProduct.getId() )) )
				{	addActionError("Cannot change the Primary Key. Delete the old record and add a new record."); 
					return "input" ; 
				} 

			// if we get here, that means we detected at least one change.  So apply all changes to the object,
			//    persist it, and then go back to the list app.
			StringBuffer errors = new StringBuffer() ;
			boolean firstError = true ;
			theProduct.setDescription(description) ;
			theProduct.setCurrentVersion(currentVersion) ;
			theProduct.setCreated(created) ;
			if (hasActionErrors() ) 
			{	if (adding)
			 	{	Product product = new Product();
					request.getSession(true).setAttribute("Product", product) ;
				}
				return "input"; 
			}
			try
			{	if (adding) 
				{	productService.persist(theProduct) ;
					this.addNewObjectToList(theProduct) ;
				 }
				else
					productService.merge(theProduct) ;
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
