package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "WayNode")
public class WayNode implements Serializable {
    public WayNode() {
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "WAYNODE_ID_GENERATOR")
    @org.hibernate.annotations.GenericGenerator(name = "WAYNODE_ID_GENERATOR", strategy = "native")
    private int id;

    @ManyToOne(targetEntity = Way.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "way_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode890837"))
    private Way way;

    @ManyToOne(targetEntity = Node.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "node1_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode900126"))
    private Node node1;

    @ManyToOne(targetEntity = Node.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "node2_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode870335"))
    private Node node2;

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setWay(Way value) {
        this.way = value;
    }

    public Way getWay() {
        return way;
    }

    public void setNode1(Node value) {
        this.node1 = value;
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode2(Node value) {
        this.node2 = value;
    }

    public Node getNode2() {
        return node2;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
