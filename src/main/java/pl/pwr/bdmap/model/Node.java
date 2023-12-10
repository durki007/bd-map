package pl.pwr.bdmap.model;

import java.io.Serializable;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "node", indexes = {@Index(columnList = "pos_x"), @Index(columnList = "pos_y")})
public class Node implements Serializable {
    public Node() {
    }

    @Column(name = "id", nullable = false, length = 10)
    @Id
    @GeneratedValue(generator = "NODE_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "pos_x", nullable = false, length = 10)
    private int posX;

    @Column(name = "pos_y", nullable = false, length = 10)
    private int posY;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    @Column(name = "timestamp", nullable = false)
    private java.sql.Timestamp timestamp;

    @ManyToOne(targetEntity = NodeType.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "NodeType_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKNode325335"))
    private NodeType nodeType;

    @OneToMany(mappedBy = "node", targetEntity = HistoricNodeData.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set historicNodeData = new HashSet();

    @OneToMany(mappedBy = "node1", targetEntity = WayNode.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set wayNode = new HashSet();

    @OneToMany(mappedBy = "node2", targetEntity = WayNode.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set wayNode1 = new HashSet();

    @OneToMany(mappedBy = "node", targetEntity = NodeTag.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set nodeTag = new HashSet();

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setPosX(int value) {
        this.posX = value;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int value) {
        this.posY = value;
    }

    public int getPosY() {
        return posY;
    }

    public void setIsBlocked(boolean value) {
        this.isBlocked = value;
    }

    public boolean getIsBlocked() {
        return isBlocked;
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
