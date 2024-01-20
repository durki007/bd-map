package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "historic_node_data", indexes = {@Index(columnList = "pos_x"), @Index(columnList = "pos_y")})
public class HistoricNodeData implements Serializable {
    public HistoricNodeData() { // Hibernate empty constructor
    }

    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(generator = "HISTORICNODEDATA_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "pos_x", nullable = false, length = 10)
    private double posX;

    @Column(name = "pos_y", nullable = false, length = 10)
    private double posY;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private java.sql.Timestamp timestamp;

    @Column(name = "version", nullable = false, length = 10)
    private int version;

    @ManyToOne(targetEntity = Changeset.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "changeset_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricNo115155"))
    private Changeset changeset;

    @ManyToOne(targetEntity = Node.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "node_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricNo297557"))
    private Node node;

    @OneToMany(mappedBy = "node1", targetEntity = HistoricWayNode.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    private java.util.Set<HistoricWayNode> historicWayNode = new java.util.HashSet<>();

    @OneToMany(mappedBy = "node2", targetEntity = HistoricWayNode.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    private java.util.Set<HistoricWayNode> historicWayNode1 = new java.util.HashSet<>();

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setPosX(double value) {
        this.posX = value;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosY(double value) {
        this.posY = value;
    }

    public double getPosY() {
        return posY;
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

    public void setHistoricWayNode(java.util.Set<HistoricWayNode> value) {
        this.historicWayNode = value;
    }

    public java.util.Set<HistoricWayNode> getHistoricWayNode() {
        return historicWayNode;
    }


    public void setHistoricWayNode1(java.util.Set<HistoricWayNode> value) {
        this.historicWayNode1 = value;
    }

    public java.util.Set<HistoricWayNode> getHistoricWayNode1() {
        return historicWayNode1;
    }


    public String toString() {
        return String.valueOf(getId());
    }

}
