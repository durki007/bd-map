package pl.pwr.bdmap.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@Table(name="`Key`")
public class Key implements Serializable {
	public Key() {
		// Empty constructor
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="KEY_ID_GENERATOR", strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="value", nullable=false, length=255, unique = true)
	private String value;
	
	@OneToMany(mappedBy="key", targetEntity= KeyNodeType.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private java.util.Set<KeyNodeType> keyNodeType = new java.util.HashSet<>();
	
	@OneToMany(mappedBy="key", targetEntity= KeyWayType.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private java.util.Set<KeyWayType> keyWayType = new java.util.HashSet<>();
	
	@OneToMany(mappedBy="key", targetEntity= WayTag.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private java.util.Set<WayTag> wayTag = new java.util.HashSet<>();
	
	@OneToMany(mappedBy="key", targetEntity= NodeTag.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private java.util.Set<NodeTag> nodeTag = new java.util.HashSet<>();
	
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
	
	public void setKeyNodeType(java.util.Set<KeyNodeType> value) {
		this.keyNodeType = value;
	}
	
	public java.util.Set<KeyNodeType> getKeyNodeType() {
		return keyNodeType;
	}
	
	
	public void setKeyWayType(java.util.Set<KeyWayType> value) {
		this.keyWayType = value;
	}
	
	public java.util.Set<KeyWayType> getKeyWayType() {
		return keyWayType;
	}
	
	
	public void setWayTag(java.util.Set<WayTag> value) {
		this.wayTag = value;
	}
	
	public java.util.Set<WayTag> getWayTag() {
		return wayTag;
	}
	
	
	public void setNodeTag(java.util.Set<NodeTag> value) {
		this.nodeTag = value;
	}
	
	public java.util.Set<NodeTag> getNodeTag() {
		return nodeTag;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
