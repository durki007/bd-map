package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "WayType")
public class WayType implements Serializable {
    public WayType() {
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "WAYTYPE_ID_GENERATOR")
    @org.hibernate.annotations.GenericGenerator(name = "WAYTYPE_ID_GENERATOR", strategy = "native")
    private int id;

    @Column(name = "type", nullable = false, length = 255)
    private String type;

    @OneToMany(mappedBy = "wayType", targetEntity = Way.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set way = new java.util.HashSet();

    @OneToMany(mappedBy = "wayType", targetEntity = KeyWayType.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set keyWayType = new java.util.HashSet();

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return type;
    }

    public void setWay(java.util.Set value) {
        this.way = value;
    }

    public java.util.Set getWay() {
        return way;
    }


    public void setKeyWayType(java.util.Set value) {
        this.keyWayType = value;
    }

    public java.util.Set getKeyWayType() {
        return keyWayType;
    }


    public String toString() {
        return String.valueOf(getId());
    }

}
