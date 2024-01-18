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
@Table(name="NodeType")
public class NodeType implements Serializable {
	public NodeType() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="NODETYPE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="NODETYPE_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="type", nullable=false, length=255)	
	private String type;
	
	@OneToMany(mappedBy="nodeType", targetEntity=Node.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set node = new java.util.HashSet();
	
	@OneToMany(mappedBy="nodeType", targetEntity=KeyNodeType.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set keyNodeType = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setType(String value) {
		this.type = value;
	}
	
	public String getType() {
		return type;
	}
	
	public void setNode(java.util.Set value) {
		this.node = value;
	}
	
	public java.util.Set getNode() {
		return node;
	}
	
	
	public void setKeyNodeType(java.util.Set value) {
		this.keyNodeType = value;
	}
	
	public java.util.Set getKeyNodeType() {
		return keyNodeType;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
