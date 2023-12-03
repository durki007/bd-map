package pl.pwr.bdmap.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="HistoricNodeData", indexes={ @Index(columnList="pos_x"), @Index(columnList="pos_y") })
public class HistoricNodeData implements Serializable {
	public HistoricNodeData() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="HISTORICNODEDATA_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="HISTORICNODEDATA_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="pos_x", nullable=false, length=10)	
	private int pos_x;
	
	@Column(name="pos_y", nullable=false, length=10)	
	private int pos_y;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="version", nullable=false, length=10)	
	private int version;
	
	@ManyToOne(targetEntity= Changeset.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="changeset_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricNo115155"))	
	private Changeset changeset;
	
	@ManyToOne(targetEntity= Node.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="node_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricNo297557"))	
	private Node node;
	
	@OneToMany(mappedBy="node1", targetEntity= HistoricWayNode.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicWayNode = new java.util.HashSet();
	
	@OneToMany(mappedBy="node2", targetEntity= HistoricWayNode.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicWayNode1 = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setPos_x(int value) {
		this.pos_x = value;
	}
	
	public int getPos_x() {
		return pos_x;
	}
	
	public void setPos_y(int value) {
		this.pos_y = value;
	}
	
	public int getPos_y() {
		return pos_y;
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
	
	public void setNode(Node value) {
		this.node = value;
	}
	
	public Node getNode() {
		return node;
	}
	
	public void setHistoricWayNode(java.util.Set value) {
		this.historicWayNode = value;
	}
	
	public java.util.Set getHistoricWayNode() {
		return historicWayNode;
	}
	
	
	public void setHistoricWayNode1(java.util.Set value) {
		this.historicWayNode1 = value;
	}
	
	public java.util.Set getHistoricWayNode1() {
		return historicWayNode1;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
