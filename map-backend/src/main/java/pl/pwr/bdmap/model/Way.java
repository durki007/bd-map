package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Way")
public class Way implements Serializable {
    public Way() {
        // Spring requires empty constructor
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "WAY_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private java.sql.Timestamp timestamp;

    public Changeset getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(Changeset blockedBy) {
        this.blockedBy = blockedBy;
    }

    @ManyToOne(targetEntity = Changeset.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "blockedBy_id", referencedColumnName = "id")})
    private Changeset blockedBy;

    @ManyToOne(targetEntity = WayType.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "WayType_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWay914835"))
    private WayType wayType;

    @OneToMany(mappedBy = "way", targetEntity = HistoricWayData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<HistoricWayData> historicWayData = new java.util.HashSet<>();

    @OneToMany(mappedBy = "way", targetEntity = WayNode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<WayNode> wayNode = new java.util.HashSet<>();

    @OneToMany(mappedBy = "way", targetEntity = WayTag.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<WayTag> wayTag = new java.util.HashSet<>();

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

    public void setWayType(WayType value) {
        this.wayType = value;
    }

    public WayType getWayType() {
        return wayType;
    }

    public void setHistoricWayData(java.util.Set<HistoricWayData> value) {
        this.historicWayData = value;
    }

    public java.util.Set<HistoricWayData> getHistoricWayData() {
        return historicWayData;
    }


    public void setWayNode(java.util.Set<WayNode> value) {
        this.wayNode = value;
    }

    public java.util.Set<WayNode> getWayNode() {
        return wayNode;
    }


    public void setWayTag(java.util.Set<WayTag> value) {
        this.wayTag = value;
    }

    public java.util.Set<WayTag> getWayTag() {
        return wayTag;
    }


    public String toString() {
        return String.valueOf(getId());
    }

}
