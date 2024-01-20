package pl.pwr.bdmap.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Changeset")
public class Changeset implements Serializable {
    public Changeset() { // empty constructor for Hibernate
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "CHANGESET_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private java.sql.Timestamp creationDate;

    public Timestamp getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }

    @Column(name = "close_date", nullable = true)
    private java.sql.Timestamp closeDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKChangeset205101"))
    private User user;

    @OneToMany(mappedBy = "changeset", targetEntity = HistoricNodeData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HistoricNodeData> historicNodeData = new java.util.HashSet<>();

    @OneToMany(mappedBy = "changeset", targetEntity = HistoricWayData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HistoricWayData> historicWayData = new java.util.HashSet<>();

    public Set<Way> getBlockedWays() {
        return blockedWays;
    }

    public void setBlockedWays(Set<Way> blockedWays) {
        this.blockedWays = blockedWays;
    }

    @OneToMany(mappedBy = "blockedBy", targetEntity = Way.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Way> blockedWays = new java.util.HashSet<>();

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setCreationDate(java.sql.Timestamp value) {
        this.creationDate = value;
    }

    public java.sql.Timestamp getCreationDate() {
        return creationDate;
    }

    public void setUser(User value) {
        this.user = value;
    }

    public User getUser() {
        return user;
    }

    public void setHistoricNodeData(Set<HistoricNodeData> value) {
        this.historicNodeData = value;
    }

    public Set<HistoricNodeData> getHistoricNodeData() {
        return historicNodeData;
    }


    public void setHistoricWayData(Set<HistoricWayData> value) {
        this.historicWayData = value;
    }

    public Set<HistoricWayData> getHistoricWayData() {
        return historicWayData;
    }


    public String toString() {
        return String.valueOf(getId());
    }

}
