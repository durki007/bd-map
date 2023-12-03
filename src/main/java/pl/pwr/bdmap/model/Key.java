package pl.pwr.bdmap.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="`Key`")
public class Key implements Serializable {
	public Key() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="KEY_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="KEY_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="value", nullable=false, length=255)	
	private String value;
	
	@OneToMany(mappedBy="key", targetEntity= KeyNodeType.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set keyNodeType = new java.util.HashSet();
	
	@OneToMany(mappedBy="key", targetEntity= KeyWayType.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set keyWayType = new java.util.HashSet();
	
	@OneToMany(mappedBy="key", targetEntity= WayTag.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set wayTag = new java.util.HashSet();
	
	@OneToMany(mappedBy="key", targetEntity= NodeTag.class)
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
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setKeyNodeType(java.util.Set value) {
		this.keyNodeType = value;
	}
	
	public java.util.Set getKeyNodeType() {
		return keyNodeType;
	}
	
	
	public void setKeyWayType(java.util.Set value) {
		this.keyWayType = value;
	}
	
	public java.util.Set getKeyWayType() {
		return keyWayType;
	}
	
	
	public void setWayTag(java.util.Set value) {
		this.wayTag = value;
	}
	
	public java.util.Set getWayTag() {
		return wayTag;
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
