package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "WayType")
public class WayType implements Serializable {
    public WayType() {
        // empty
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "WAYTYPE_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false, length = 255)
    private String type;

    @OneToMany(mappedBy = "wayType", targetEntity = Way.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<Way> way = new java.util.HashSet<>();

    @OneToMany(mappedBy = "wayType", targetEntity = KeyWayType.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<KeyWayType> keyWayType = new java.util.HashSet<>();

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

    public void setWay(java.util.Set<Way> value) {
        this.way = value;
    }

    public java.util.Set<Way> getWay() {
        return way;
    }


    public void setKeyWayType(java.util.Set<KeyWayType> value) {
        this.keyWayType = value;
    }

    public java.util.Set<KeyWayType> getKeyWayType() {
        return keyWayType;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
