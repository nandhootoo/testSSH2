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
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.nandhootoo.domain.Issue;

public interface IssueService extends AbstractService {


	public List getIssuesWithCriteria(String criteria);

	public Issue findByKey(Integer key);

	public void persist(Issue issue);

	public void merge(Issue issue);

	public void delete (Issue issue) ;

	public void deleteItems (List<Integer> numbers, List<Issue> issues) ;

	public void deleteKey (Integer id) ;

}
