
package com.nandhootoo.actions;  

import javax.servlet.http.HttpServletRequest;  

import com.opensymphony.xwork2.ActionSupport;  
/* ************************************************************   
 *                                                                
 *   All Action classes should hang off this.      
 *                                                                
 * ************************************************************/  


public class BaseAction extends ActionSupport {  

	protected HttpServletRequest request ;  

	public void setServletRequest(HttpServletRequest httpServletRequest)   
	{	request = httpServletRequest ;  
	}  

	public boolean equals (Object a, Object b)  
	{	// Null-safe compare  
		return a == null ? b == null : a.equals(b) ;  
	} 
}  