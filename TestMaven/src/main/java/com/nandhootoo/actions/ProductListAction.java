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
public class ProductListAction extends BaseAction implements ServletRequestAware {

	private Product theProduct ;
	private ProductService productService ;
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
	private List<Product> listToDisplay = new ArrayList() ;


	/* ********************************************************************************************** 
	 * *    Spring-injected constructor                                                               
	 * **********************************************************************************************/
	public ProductListAction(ProductService passedService)
	{	this.productService = passedService ;
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
	{	listToDisplay =  (List<Product>) request.getSession(true).getAttribute("ProductList"); 
		return listToDisplay ;
	}

	public void setSelectStatement(String val)
	{	selectStatement = val ;
	    request.getSession(true).setAttribute("ProductQuery", selectStatement) ;
	}

	public String getSelectStatement()
	{	String selectStatement = (String)request.getSession(true).getAttribute("ProductQuery"); 
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
	public List getProducts()
	{	// Notify the service to issue the query; catch any errors here, and display them in the errorField
		try
		{	List<Product> theList = productService.getProductsWithCriteria(selectStatement) ;
			request.getSession(true).setAttribute("ProductList", theList) ; 
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
	 * *    Get the Product that the display or edit page will display   
	 * **********************************************************************************************/
	public Product getTheProduct()  
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
	* * Callback methods from the individual links within the table.
	 * **********************************************************************************************/

	public String display() 
	{	// Get the Product record, stuff it into the session object, then invoke the Displayer 
		List<Product> theList = (List)request.getSession(true).getAttribute("ProductList") ;  
		Product theProduct = theList.get(index) ;
		request.getSession(true).setAttribute("Product", theProduct); 
		return "displaySingle" ; 
	} 

	public String edit() 
	{	// Get the Product record, stuff it into the session object, then invoke the Displayer 
		List<Product> theList = (List)request.getSession(true).getAttribute("ProductList") ;  
		Product theProduct = theList.get(index) ;
		request.getSession(true).setAttribute("Product", theProduct); 
		return "editSingle" ; 
	} 

	public String remove() 		
	{	try
		{	listToDisplay = (List)request.getSession(true).getAttribute("ProductList") ;  
			Product theProduct = listToDisplay.get(index) ;
			productService.delete(theProduct); 
			listToDisplay.remove(theProduct); 
			request.getSession(true).setAttribute("ProductList", listToDisplay); 
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
			List<Product> theList = (List)request.getSession(true).getAttribute("ProductList") ;  
			try { productService.deleteItems(selectedItems, theList ); 		
			}  
			catch (Exception err)  
			{ 	addActionError(err.getCause().getMessage() ) ; 
				return "refresh" ; 
			}  
			// Now spin through the saved list, find the Products we deleted,   		
			//     and just remove them from the list.   		
			List<Product> copiedList = new ArrayList() ; 
			copiedList.addAll(theList) ; 
			for (Integer each : selectedItems) 		
			{	Product aProduct = copiedList.get(each) ; 
				theList.remove(aProduct) ; 		
			} 		
			request.getSession(true).setAttribute("ProductList", theList); 		 		
			tableCount = theList.size() ; 		 		
			return "refresh" ; 		
		}
		if (queryButtonPressed)
		{	
			listToDisplay = this.getProducts()  ;
			return "refresh" ;
		}
		if (addNewButtonPressed)
		{	// Create an empty Product object, stuff it into the session, and invoke the Editor on it
			Product product = new Product();
	    	request.getSession(true).setAttribute("Product", product) ;
			return "editSingle" ;
		}
		if (returnButtonPressed)
		{	// If return was hit, remove the list of items from the session.  Save some memory this way. 
			request.getSession(true).removeAttribute("ProductList"); 
			return "returnToMainApp" ;
		}
		return "refresh" ;
	}
}
