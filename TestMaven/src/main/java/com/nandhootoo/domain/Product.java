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
@Table(name="products", schema="")
public class Product implements java.io.Serializable
{


	// Empty initializer 
	public Product()
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

	@Column (name="description", nullable=false, length=24)
	public String description;
	public String getDescription () 
	{	return description ; } 

	public void setDescription(String description)
	{	this.description = description; } 

	@Column (name="current_version", nullable=false, length=15)
	public String currentVersion;
	public String getCurrentVersion () 
	{	return currentVersion ; } 

	public void setCurrentVersion(String currentVersion)
	{	this.currentVersion = currentVersion; } 

	@Column (name="created", nullable=false)
	public Timestamp created;
	public Timestamp getCreated () 
	{	return created ; } 

	public void setCreated(Timestamp created)
	{	this.created = created; } 

	/****************************************************************
	 *   From exported foreign keys
	 ****************************************************************/
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="issuesIbfk1")
	@ElementCollection 
	@Embedded
	private Set<Issue> issuesIbfk1Ex = new HashSet<Issue> (0);
	public Set<Issue> getIssuesIbfk1Ex()
	{	return issuesIbfk1Ex ;
	}
	
	public void setIssuesIbfk1Ex(Set<Issue> passedIssuesIbfk1Ex)
	{	issuesIbfk1Ex = passedIssuesIbfk1Ex ;
	}
	

	/****************************************************************
	 *   End of foreign keys
	 ****************************************************************/
	public String toString()
	{	return "Product" ;
	}
}
