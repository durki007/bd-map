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
@Table(name="HistoricWayNode")
public class HistoricWayNode implements Serializable {
	public HistoricWayNode() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=10)	
	@Id	
	@GeneratedValue(generator="HISTORICWAYNODE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="HISTORICWAYNODE_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="version", nullable=false, length=10)	
	private int version;
	
	@ManyToOne(targetEntity=HistoricNodeData.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="node1_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricWa603500"))	
	private HistoricNodeData node1;
	
	@ManyToOne(targetEntity=HistoricNodeData.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="node2_id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricWa633291"))	
	private HistoricNodeData node2;
	
	@ManyToOne(targetEntity=HistoricWayData.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="HistoricWayData_Id", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKHistoricWa481251"))	
	private HistoricWayData historicWayData;
	
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
	
	public void setNode1(HistoricNodeData value) {
		this.node1 = value;
	}
	
	public HistoricNodeData getNode1() {
		return node1;
	}
	
	public void setNode2(HistoricNodeData value) {
		this.node2 = value;
	}
	
	public HistoricNodeData getNode2() {
		return node2;
	}
	
	public void setHistoricWayData(HistoricWayData value) {
		this.historicWayData = value;
	}
	
	public HistoricWayData getHistoricWayData() {
		return historicWayData;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
