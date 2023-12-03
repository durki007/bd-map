/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: durki(Wrocław University of Science and Technology)
 * License Type: Academic
 */
import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="`User`")
public class User implements Serializable {
	public User() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="USER_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="USER_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="username", nullable=false, unique=true, length=255)	
	private String username;
	
	@Column(name="email", nullable=false, unique=true, length=255)	
	private String email;
	
	@Column(name="password", nullable=false, length=255)	
	private String password;
	
	@Column(name="role", nullable=false, length=255)	
	private String role;
	
	@OneToMany(mappedBy="user", targetEntity=Changeset.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set changeset = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRole(String value) {
		this.role = value;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setChangeset(java.util.Set value) {
		this.changeset = value;
	}
	
	public java.util.Set getChangeset() {
		return changeset;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
