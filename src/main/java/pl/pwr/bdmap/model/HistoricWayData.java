package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "HistoricWayData")
public class HistoricWayData implements Serializable {
    public HistoricWayData() {
        // Hibernate Empty constructor
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "HISTORICWAYDATA_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private java.sql.Timestamp timestamp;

    @Column(name = "version", nullable = false, length = 10)
    private int version;

    @ManyToOne(targetEntity = Changeset.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "changeset_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricWa184112"))
    private Changeset changeset;

    @ManyToOne(targetEntity = Way.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "way_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKHistoricWa730677"))
    private Way way;

    @OneToMany(mappedBy = "historicWayData", targetEntity = HistoricWayNode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<HistoricWayNode> historicWayNode = new java.util.HashSet<>();

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
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

    public void setWay(Way value) {
        this.way = value;
    }

    public Way getWay() {
        return way;
    }

    public void setHistoricWayNode(java.util.Set<HistoricWayNode> value) {
        this.historicWayNode = value;
    }

    public java.util.Set<HistoricWayNode> getHistoricWayNode() {
        return historicWayNode;
    }


    public String toString() {
        return String.valueOf(getId());
    }

}
