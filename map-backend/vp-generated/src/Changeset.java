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
@Table(name="Changeset")
public class Changeset implements Serializable {
	public Changeset() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="CHANGESET_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CHANGESET_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="creation_date", nullable=false)	
	private java.sql.Timestamp creation_date;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="user_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKChangeset205101"))	
	private User user;
	
	@OneToMany(mappedBy="changeset", targetEntity=HistoricNodeData.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicNodeData = new java.util.HashSet();
	
	@OneToMany(mappedBy="changeset", targetEntity=HistoricWayData.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicWayData = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setCreation_date(java.sql.Timestamp value) {
		this.creation_date = value;
	}
	
	public java.sql.Timestamp getCreation_date() {
		return creation_date;
	}
	
	public void setUser(User value) {
		this.user = value;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setHistoricNodeData(java.util.Set value) {
		this.historicNodeData = value;
	}
	
	public java.util.Set getHistoricNodeData() {
		return historicNodeData;
	}
	
	
	public void setHistoricWayData(java.util.Set value) {
		this.historicWayData = value;
	}
	
	public java.util.Set getHistoricWayData() {
		return historicWayData;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
