package pl.pwr.bdmap.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "WayNode")
public class WayNode implements Serializable {
    public WayNode() {
        // Empty constructor required by Hibernate
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "WAYNODE_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Way.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "way_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode890837"))
    private Way way;

    @ManyToOne(targetEntity = Node.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "node1_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode900126"))
    private Node node1;

    @ManyToOne(targetEntity = Node.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(value = {@JoinColumn(name = "node2_id", referencedColumnName = "id", nullable = false)}, foreignKey = @ForeignKey(name = "FKWayNode870335"))
    private Node node2;


    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    public Set<HistoricWayNode> getHistoricWayNode() {
        return historicWayNode;
    }

    public void setHistoricWayNode(Set<HistoricWayNode> historicNodeData) {
        this.historicWayNode = historicNodeData;
    }

    @OneToMany(mappedBy = "node", targetEntity = HistoricWayNode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<HistoricWayNode> historicWayNode = new HashSet<>();

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }


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
