package com.nandhootoo.domain;

import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.ElementCollection;

@Entity
@Embeddable
@Table(name="statuses", schema="")
public class Status implements java.io.Serializable
{


	// Empty initializer 
	public Status()
	{} ;

	public boolean hasNullKey()
	{	return (id == null  ) ;	
	}


	static final long serialVersionUID = 0L ; 

	@GeneratedValue 
	@Id 
	@Column (name="id", nullable=false)
	public Integer id;
	public Integer getId () 
	{	return id ; } 

	public void setId(Integer id)
	{	this.id = id; } 

	@Column (name="description", nullable=true, length=15)
	public String description;
	public String getDescription () 
	{	return description ; } 

	public void setDescription(String description)
	{	this.description = description; } 

	/****************************************************************
	 *   From exported foreign keys
	 ****************************************************************/
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="issuesIbfk2")
	@ElementCollection 
	@Embedded
	private Set<Issue> issuesIbfk2Ex = new HashSet<Issue> (0);
	public Set<Issue> getIssuesIbfk2Ex()
	{	return issuesIbfk2Ex ;
	}
	
	public void setIssuesIbfk2Ex(Set<Issue> passedIssuesIbfk2Ex)
	{	issuesIbfk2Ex = passedIssuesIbfk2Ex ;
	}
	

	/****************************************************************
	 *   End of foreign keys
	 ****************************************************************/
	public String toString()
	{	return "Status" ;
	}
}
