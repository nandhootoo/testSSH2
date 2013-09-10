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
package com.nandhootoo.services;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.nandhootoo.domain.Product;
import javax.persistence.EntityManager; 
import javax.persistence.EntityTransaction; 
import javax.persistence.Persistence; 
import javax.persistence.PersistenceContext; 
import javax.persistence.Query; 
import org.springframework.transaction.annotation.Transactional ; 

@Transactional 
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService  {

	private EntityManager entityManager ;	
	
	@PersistenceContext	
	public void setEntityManager (EntityManager passedEntityManager) 	 
	{	entityManager = passedEntityManager ;	
	}	
	
	
	public List getProductsWithCriteria (String criteria) 
	{	try 
		{	Query aQuery = entityManager.createQuery("select product from Product as product " + criteria ) ; 
			return aQuery.getResultList() ; 
		}	
		catch (Exception sqlex) 
		{  System.out.println("SQL Exception: " +  sqlex.getMessage()  ) ; 
			return null ;
		}	
	}	
	
	public Product findByKey (Integer product) 
	{	return entityManager.find(Product.class, product);
	}	
	
	public void persist(Product product) 
	{	EntityTransaction tx = null ;		
		try {
			entityManager.persist(product);	
		}		
		catch (Exception ex)		
		{	if (tx != null && tx.isActive()	)	
				tx.rollback() ;
			throw (RuntimeException)ex.getCause() ;	
		}		
	}			
	
	public void merge(Product product) 
	{	EntityTransaction tx = null ;		
		try {
			entityManager.merge(product);	
		}		
		catch (Exception ex)		
		{	if (tx != null && tx.isActive()	)	
				tx.rollback() ;
			throw (RuntimeException)ex.getCause() ;	
		}		
	}			
	
	public void delete(Product product) 
	{	EntityTransaction tx = null ;		
		this.deleteKey( 
			product.getId()	) ;
	}			
	
	public void deleteItems (List<Integer> numbers, List<Product> products) 
	{	EntityTransaction tx = null ;		
		int cntr = 0 ;			
		for (Integer anInt : numbers)	
		{	Product anProduct = products.get(anInt) ; 
			this.deleteKey(anProduct.getId() ) ;
			cntr++ ; 
		}	
		System.out.println("" + cntr + " products deleted" ); 	
	}	


	public void deleteKey(Integer id)
	{  
	 	try {			  
	 		String hqlDelete = "delete Product where id = :idString ";
			int deletedEntries = entityManager.createQuery(hqlDelete) 
					.setParameter("idString", id)
					.executeUpdate() ;  
	 		}  
	 	catch (Exception ex)		
	 	{	throw (RuntimeException) ex.getCause(); 		
	 	}				
	 }	

}
