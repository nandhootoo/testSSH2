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
@Table(name="users", schema="")
public class User implements java.io.Serializable
{


	// Empty initializer 
	public User()
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

	@Column (name="username", nullable=false, length=15)
	public String username;
	public String getUsername () 
	{	return username ; } 

	public void setUsername(String username)
	{	this.username = username; } 

	@Column (name="firstname", nullable=false, length=24)
	public String firstname;
	public String getFirstname () 
	{	return firstname ; } 

	public void setFirstname(String firstname)
	{	this.firstname = firstname; } 

	@Column (name="lastname", nullable=false, length=24)
	public String lastname;
	public String getLastname () 
	{	return lastname ; } 

	public void setLastname(String lastname)
	{	this.lastname = lastname; } 

	@Column (name="department", nullable=false)
	public Integer department;
	public Integer getDepartment () 
	{	return department ; } 

	public void setDepartment(Integer department)
	{	this.department = department; } 

	@Column (name="created", nullable=false)
	public Timestamp created;
	public Timestamp getCreated () 
	{	return created ; } 

	public void setCreated(Timestamp created)
	{	this.created = created; } 

	/****************************************************************
	 *   From exported foreign keys
	 ****************************************************************/
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="issuesIbfk3ForId")
	@ElementCollection 
	@Embedded
	private Set<Issue> issuesIbfk3Ex = new HashSet<Issue> (0);
	public Set<Issue> getIssuesIbfk3Ex()
	{	return issuesIbfk3Ex ;
	}
	
	public void setIssuesIbfk3Ex(Set<Issue> passedIssuesIbfk3Ex)
	{	issuesIbfk3Ex = passedIssuesIbfk3Ex ;
	}
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="issuesIbfk4ForId")
	@ElementCollection 
	@Embedded
	private Set<Issue> issuesIbfk4Ex = new HashSet<Issue> (0);
	public Set<Issue> getIssuesIbfk4Ex()
	{	return issuesIbfk4Ex ;
	}
	
	public void setIssuesIbfk4Ex(Set<Issue> passedIssuesIbfk4Ex)
	{	issuesIbfk4Ex = passedIssuesIbfk4Ex ;
	}
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="notesIbfk2")
	@ElementCollection 
	@Embedded
	private Set<Note> notesIbfk2Ex = new HashSet<Note> (0);
	public Set<Note> getNotesIbfk2Ex()
	{	return notesIbfk2Ex ;
	}
	
	public void setNotesIbfk2Ex(Set<Note> passedNotesIbfk2Ex)
	{	notesIbfk2Ex = passedNotesIbfk2Ex ;
	}
	

	/****************************************************************
	 *   From imported foreign keys
	 ****************************************************************/
	@ManyToOne (fetch=FetchType.EAGER, targetEntity=Department.class)
	@ForeignKey (name="users_ibfk_1")
	@JoinColumn (name="department", nullable=false, referencedColumnName="id", insertable=false, updatable=false)
	private Department usersIbfk1 ;
	public Department getUsersIbfk1()
	{	return usersIbfk1 ;
	}
	
	public void setUsersIbfk1(Department passedUsersIbfk1)
	{	usersIbfk1 = passedUsersIbfk1 ;
	}
	

	/****************************************************************
	 *   End of foreign keys
	 ****************************************************************/
	public String toString()
	{	return "User" ;
	}
}
