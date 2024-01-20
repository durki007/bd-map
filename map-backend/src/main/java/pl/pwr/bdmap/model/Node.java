package pl.pwr.bdmap.model;

import java.io.Serializable;
import java.util.HashSet;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "node", indexes = {@Index(columnList = "pos_x"), @Index(columnList = "pos_y")})
public class Node implements Serializable {
    public Node() {
        // Hibernate empty constructor
    }

    public Node(Node node) {
        this.posX = node.posX;
        this.posY = node.posY;
        this.isBlocked = node.isBlocked;
        this.timestamp = node.timestamp;
        this.nodeType = node.nodeType;
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
    @CreationTimestamp
    private java.sql.Timestamp timestamp;

    @ManyToOne(targetEntity = NodeType.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "NodeType_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKNode325335"))
    private NodeType nodeType;

    @OneToMany(mappedBy = "node", targetEntity = HistoricNodeData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<HistoricNodeData> historicNodeData = new HashSet<>();

    @OneToMany(mappedBy = "node1", targetEntity = WayNode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<WayNode> wayNode = new HashSet<>();

    @OneToMany(mappedBy = "node2", targetEntity = WayNode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<WayNode> wayNode1 = new HashSet<>();

    @OneToMany(mappedBy = "node", targetEntity = NodeTag.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<NodeTag> nodeTag = new HashSet<>();

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

    public void setHistoricNodeData(java.util.Set<HistoricNodeData> value) {
        this.historicNodeData = value;
    }

    public java.util.Set<HistoricNodeData> getHistoricNodeData() {
        return historicNodeData;
    }


    public void setWayNode(java.util.Set<WayNode> value) {
        this.wayNode = value;
    }

    public java.util.Set<WayNode> getWayNode() {
        return wayNode;
    }


    public void setWayNode1(java.util.Set<WayNode> value) {
        this.wayNode1 = value;
    }

    public java.util.Set<WayNode> getWayNode1() {
        return wayNode1;
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
