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
@Table(name="Node", indexes={ @Index(columnList="pos_x"), @Index(columnList="pos_y") })
public class Node implements Serializable {
	public Node() {
	}
	
	@Column(name="id", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="NODE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="NODE_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="pos_x", nullable=false, length=10)	
	private int pos_x;
	
	@Column(name="pos_y", nullable=false, length=10)	
	private int pos_y;
	
	@Column(name="is_blocked", nullable=false)	
	private boolean is_blocked;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@ManyToOne(targetEntity=NodeType.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="NodeType_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKNode325335"))	
	private NodeType nodeType;
	
	@OneToMany(mappedBy="node", targetEntity=HistoricNodeData.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historicNodeData = new java.util.HashSet();
	
	@OneToMany(mappedBy="node1", targetEntity=WayNode.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set wayNode = new java.util.HashSet();
	
	@OneToMany(mappedBy="node2", targetEntity=WayNode.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set wayNode1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="node", targetEntity=NodeTag.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set nodeTag = new java.util.HashSet();
	
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
	
	public void setIs_blocked(boolean value) {
		this.is_blocked = value;
	}
	
	public boolean getIs_blocked() {
		return is_blocked;
	}
	
	public void setTimestamp(java.sql.Timestamp value) {
		this.timestamp = value;
	}
	
	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setNodeType(NodeType value) {
		this.nodeType = value;
	}
	
	public NodeType getNodeType() {
		return nodeType;
	}
	
	public void setHistoricNodeData(java.util.Set value) {
		this.historicNodeData = value;
	}
	
	public java.util.Set getHistoricNodeData() {
		return historicNodeData;
	}
	
	
	public void setWayNode(java.util.Set value) {
		this.wayNode = value;
	}
	
	public java.util.Set getWayNode() {
		return wayNode;
	}
	
	
	public void setWayNode1(java.util.Set value) {
		this.wayNode1 = value;
	}
	
	public java.util.Set getWayNode1() {
		return wayNode1;
	}
	
	
	public void setNodeTag(java.util.Set value) {
		this.nodeTag = value;
	}
	
	public java.util.Set getNodeTag() {
		return nodeTag;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
