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
@Table(name="NodeTag")
public class NodeTag implements Serializable {
	public NodeTag() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="NODETAG_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="NODETAG_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="version", nullable=false, length=10)	
	private int version;
	
	@ManyToOne(targetEntity=Node.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="Node_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKNodeTag305944"))	
	private Node node;
	
	@ManyToOne(targetEntity=Key.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="Key_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKNodeTag365389"))	
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
	
	public void setVersion(int value) {
		this.version = value;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setNode(Node value) {
		this.node = value;
	}
	
	public Node getNode() {
		return node;
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
