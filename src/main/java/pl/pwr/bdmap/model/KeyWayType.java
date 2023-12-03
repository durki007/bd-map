package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "KeyWayType")
public class KeyWayType implements Serializable {
    public KeyWayType() {
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "KEYWAYTYPE_ID_GENERATOR")
    @org.hibernate.annotations.GenericGenerator(name = "KEYWAYTYPE_ID_GENERATOR", strategy = "native")
    private int id;

    @ManyToOne(targetEntity = WayType.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "WayType_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKKeyWayType600077"))
    private WayType wayType;

    @ManyToOne(targetEntity = Key.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "Key_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKKeyWayType814054"))
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

    public void setWayType(WayType value) {
        this.wayType = value;
    }

    public WayType getWayType() {
        return wayType;
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
