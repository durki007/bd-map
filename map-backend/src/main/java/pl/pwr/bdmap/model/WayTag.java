package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "WayTag")
public class WayTag implements Serializable {
    public WayTag() {
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "WAYTAG_ID_GENERATOR")
    @org.hibernate.annotations.GenericGenerator(name = "WAYTAG_ID_GENERATOR", strategy = "native")
    private int id;

    @Column(name = "version", nullable = false, length = 10)
    private int version;

    @ManyToOne(targetEntity = Way.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "Way_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayTag146921"))
    private Way way;

    @ManyToOne(targetEntity = Key.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "Key_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayTag279445"))
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

    public void setWay(Way value) {
        this.way = value;
    }

    public Way getWay() {
        return way;
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
