package pl.pwr.bdmap.model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "HistoricWayNode")
public class HistoricWayNode implements Serializable {
    public HistoricWayNode() {
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "HISTORICWAYNODE_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private java.sql.Timestamp timestamp;

    public HistoricWayData getWay() {
        return way;
    }

    public void setWay(HistoricWayData way) {
        this.way = way;
    }

    @ManyToOne(targetEntity = HistoricWayData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "way_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode890837"))
    private HistoricWayData way;

    @ManyToOne(targetEntity = HistoricNodeData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "node1_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricWa603500"))
    private HistoricNodeData node1;

    @ManyToOne(targetEntity = HistoricNodeData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "node2_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricWa633291"))
    private HistoricNodeData node2;

    @ManyToOne(targetEntity = HistoricWayData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "HistoricWayData_Id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricWa481251"))
    private HistoricWayData historicWayData;

    public Changeset getChangeset() {
        return changeset;
    }

    public void setChangeset(Changeset changeset) {
        this.changeset = changeset;
    }

    @ManyToOne(targetEntity = Changeset.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "changeset_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricWa184112"))
    private Changeset changeset;

    public WayNode getWayNode() {
        return wayNode;
    }

    public void setWayNode(WayNode wayNode) {
        this.wayNode = wayNode;
    }

    @ManyToOne(targetEntity = WayNode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "way_node_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey())
    private WayNode wayNode;

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
