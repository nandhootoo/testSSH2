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
@Table(name="notes", schema="")
public class Note implements java.io.Serializable
{


	// Empty initializer 
	public Note()
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

	@Column (name="description", nullable=false, length=255)
	public String description;
	public String getDescription () 
	{	return description ; } 

	public void setDescription(String description)
	{	this.description = description; } 

	@Column (name="issue", nullable=false)
	public Integer issue;
	public Integer getIssue () 
	{	return issue ; } 

	public void setIssue(Integer issue)
	{	this.issue = issue; } 

	@Column (name="creator", nullable=false)
	public Integer creator;
	public Integer getCreator () 
	{	return creator ; } 

	public void setCreator(Integer creator)
	{	this.creator = creator; } 

	@Column (name="created", nullable=false)
	public Timestamp created;
	public Timestamp getCreated () 
	{	return created ; } 

	public void setCreated(Timestamp created)
	{	this.created = created; } 

	/****************************************************************
	 *   From imported foreign keys
	 ****************************************************************/
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=Issue.class)
	@ForeignKey (name="notes_ibfk_1")
	@JoinColumn (name="issue", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private Issue notesIbfk1 ;
	public Issue getNotesIbfk1()
	{	return notesIbfk1 ;
	}
	
	public void setNotesIbfk1(Issue passedNotesIbfk1)
	{	notesIbfk1 = passedNotesIbfk1 ;
	}
	
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=User.class)
	@ForeignKey (name="notes_ibfk_2")
	@JoinColumn (name="creator", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private User notesIbfk2 ;
	public User getNotesIbfk2()
	{	return notesIbfk2 ;
	}
	
	public void setNotesIbfk2(User passedNotesIbfk2)
	{	notesIbfk2 = passedNotesIbfk2 ;
	}
	

	/****************************************************************
	 *   End of foreign keys
	 ****************************************************************/
	public String toString()
	{	return "Note" ;
	}
}
