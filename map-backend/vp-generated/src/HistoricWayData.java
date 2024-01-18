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
@Table(name="HistoricWayData")
public class HistoricWayData implements Serializable {
	public HistoricWayData() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="HISTORICWAYDATA_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="HISTORICWAYDATA_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="name", nullable=false, length=255)	
	private String name;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="version", nullable=false, length=10)	
	private int version;
	
	@ManyToOne(targetEntity=Changeset.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="changeset_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricWa184112"))	
	private Changeset changeset;
	
	@ManyToOne(targetEntity=Way.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="way_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricWa730677"))	
	private Way way;
	
	@OneToMany(mappedBy="historicWayData", targetEntity=HistoricWayNode.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicWayNode = new java.util.HashSet();
	
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
	
	public void setVersion(int value) {
		this.version = value;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setChangeset(Changeset value) {
		this.changeset = value;
	}
	
	public Changeset getChangeset() {
		return changeset;
	}
	
	public void setWay(Way value) {
		this.way = value;
	}
	
	public Way getWay() {
		return way;
	}
	
	public void setHistoricWayNode(java.util.Set value) {
		this.historicWayNode = value;
	}
	
	public java.util.Set getHistoricWayNode() {
		return historicWayNode;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
