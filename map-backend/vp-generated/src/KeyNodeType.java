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
@Table(name="KeyNodeType")
public class KeyNodeType implements Serializable {
	public KeyNodeType() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="KEYNODETYPE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="KEYNODETYPE_ID_GENERATOR", strategy="native")	
	private int id;
	
	@ManyToOne(targetEntity=NodeType.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="NodeType_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKKeyNodeTyp917879"))	
	private NodeType nodeType;
	
	@ManyToOne(targetEntity=Key.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="Key_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKKeyNodeTyp52570"))	
	private Key key;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setNodeType(NodeType value) {
		this.nodeType = value;
	}
	
	public NodeType getNodeType() {
		return nodeType;
	}
	
	public void setKey(Key value) {
		this.key = value;
	}
	
	public Key getKey() {
		return key;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
