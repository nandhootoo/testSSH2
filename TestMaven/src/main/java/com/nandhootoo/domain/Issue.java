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
@Table(name="issues", schema="")
public class Issue implements java.io.Serializable
{


	// Empty initializer 
	public Issue()
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

	@Column (name="product", nullable=false)
	public Integer product;
	public Integer getProduct () 
	{	return product ; } 

	public void setProduct(Integer product)
	{	this.product = product; } 

	@Column (name="version", nullable=false, length=15)
	public String version;
	public String getVersion () 
	{	return version ; } 

	public void setVersion(String version)
	{	this.version = version; } 

	@Column (name="status", nullable=false)
	public Integer status;
	public Integer getStatus () 
	{	return status ; } 

	public void setStatus(Integer status)
	{	this.status = status; } 

	@Column (name="description", nullable=false, length=255)
	public String description;
	public String getDescription () 
	{	return description ; } 

	public void setDescription(String description)
	{	this.description = description; } 

	@Column (name="creator", nullable=false)
	public Integer creator;
	public Integer getCreator () 
	{	return creator ; } 

	public void setCreator(Integer creator)
	{	this.creator = creator; } 

	@Column (name="owner", nullable=false)
	public Integer owner;
	public Integer getOwner () 
	{	return owner ; } 

	public void setOwner(Integer owner)
	{	this.owner = owner; } 

	@Column (name="created", nullable=false)
	public Timestamp created;
	public Timestamp getCreated () 
	{	return created ; } 

	public void setCreated(Timestamp created)
	{	this.created = created; } 

	/****************************************************************
	 *   From exported foreign keys
	 ****************************************************************/
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="notesIbfk1")
	@ElementCollection 
	@Embedded
	private Set<Note> notesIbfk1Ex = new HashSet<Note> (0);
	public Set<Note> getNotesIbfk1Ex()
	{	return notesIbfk1Ex ;
	}
	
	public void setNotesIbfk1Ex(Set<Note> passedNotesIbfk1Ex)
	{	notesIbfk1Ex = passedNotesIbfk1Ex ;
	}
	

	/****************************************************************
	 *   From imported foreign keys
	 ****************************************************************/
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=Product.class)
	@ForeignKey (name="issues_ibfk_1")
	@JoinColumn (name="product", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private Product issuesIbfk1 ;
	public Product getIssuesIbfk1()
	{	return issuesIbfk1 ;
	}
	
	public void setIssuesIbfk1(Product passedIssuesIbfk1)
	{	issuesIbfk1 = passedIssuesIbfk1 ;
	}
	
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=Status.class)
	@ForeignKey (name="issues_ibfk_2")
	@JoinColumn (name="status", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private Status issuesIbfk2 ;
	public Status getIssuesIbfk2()
	{	return issuesIbfk2 ;
	}
	
	public void setIssuesIbfk2(Status passedIssuesIbfk2)
	{	issuesIbfk2 = passedIssuesIbfk2 ;
	}
	
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=User.class)
	@ForeignKey (name="issues_ibfk_3")
	@JoinColumn (name="creator", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private User issuesIbfk3ForId ;
	public User getIssuesIbfk3ForId()
	{	return issuesIbfk3ForId ;
	}
	
	public void setIssuesIbfk3ForId(User passedIssuesIbfk3ForId)
	{	issuesIbfk3ForId = passedIssuesIbfk3ForId ;
	}
	
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=User.class)
	@ForeignKey (name="issues_ibfk_4")
	@JoinColumn (name="owner", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private User issuesIbfk4ForId ;
	public User getIssuesIbfk4ForId()
	{	return issuesIbfk4ForId ;
	}
	
	public void setIssuesIbfk4ForId(User passedIssuesIbfk4ForId)
	{	issuesIbfk4ForId = passedIssuesIbfk4ForId ;
	}
	

	/****************************************************************
	 *   End of foreign keys
	 ****************************************************************/
	public String toString()
	{	return "Issue" ;
	}
}
