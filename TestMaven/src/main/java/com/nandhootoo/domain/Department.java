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
@Table(name="departments", schema="")
public class Department implements java.io.Serializable
{


	// Empty initializer 
	public Department()
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

	@Column (name="name", nullable=false, length=24)
	public String name;
	public String getName () 
	{	return name ; } 

	public void setName(String name)
	{	this.name = name; } 

	@Column (name="description", nullable=true, length=255)
	public String description;
	public String getDescription () 
	{	return description ; } 

	public void setDescription(String description)
	{	this.description = description; } 

	@Column (name="created", nullable=false)
	public Timestamp created;
	public Timestamp getCreated () 
	{	return created ; } 

	public void setCreated(Timestamp created)
	{	this.created = created; } 

	/****************************************************************
	 *   From exported foreign keys
	 ****************************************************************/
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="usersIbfk1")
	@ElementCollection 
	@Embedded
	private Set<User> usersIbfk1Ex = new HashSet<User> (0);
	public Set<User> getUsersIbfk1Ex()
	{	return usersIbfk1Ex ;
	}
	
	public void setUsersIbfk1Ex(Set<User> passedUsersIbfk1Ex)
	{	usersIbfk1Ex = passedUsersIbfk1Ex ;
	}
	

	/****************************************************************
	 *   End of foreign keys
	 ****************************************************************/
	public String toString()
	{	return "Department" ;
	}
}
