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
@Table(name="Way")
public class Way implements Serializable {
	public Way() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="WAY_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="WAY_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="name", nullable=false, length=255)	
	private String name;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="is_blocked", nullable=false)	
	private boolean is_blocked;
	
	@ManyToOne(targetEntity=WayType.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="WayType_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKWay914835"))	
	private WayType wayType;
	
	@OneToMany(mappedBy="way", targetEntity=HistoricWayData.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicWayData = new java.util.HashSet();
	
	@OneToMany(mappedBy="way", targetEntity=WayNode.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set wayNode = new java.util.HashSet();
	
	@OneToMany(mappedBy="way", targetEntity=WayTag.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set wayTag = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTimestamp(java.sql.Timestamp value) {
		this.timestamp = value;
	}
	
	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setIs_blocked(boolean value) {
		this.is_blocked = value;
	}
	
	public boolean getIs_blocked() {
		return is_blocked;
	}
	
	public void setWayType(WayType value) {
		this.wayType = value;
	}
	
	public WayType getWayType() {
		return wayType;
	}
	
	public void setHistoricWayData(java.util.Set value) {
		this.historicWayData = value;
	}
	
	public java.util.Set getHistoricWayData() {
		return historicWayData;
	}
	
	
	public void setWayNode(java.util.Set value) {
		this.wayNode = value;
	}
	
	public java.util.Set getWayNode() {
		return wayNode;
	}
	
	
	public void setWayTag(java.util.Set value) {
		this.wayTag = value;
	}
	
	public java.util.Set getWayTag() {
		return wayTag;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
