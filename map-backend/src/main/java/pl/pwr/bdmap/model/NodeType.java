package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "NodeType")
public class NodeType implements Serializable {
    public NodeType() {
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "NODETYPE_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false, unique = true, length = 255)
    private String type;

    @OneToMany(mappedBy = "nodeType", targetEntity = Node.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<Node> node = new java.util.HashSet<>();

    @OneToMany(mappedBy = "nodeType", targetEntity = KeyNodeType.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<KeyNodeType> keyNodeType = new java.util.HashSet<>();

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

    public void setNode(java.util.Set<Node> value) {
        this.node = value;
    }

    public java.util.Set<Node> getNode() {
        return node;
    }

    public void setKeyNodeType(java.util.Set<KeyNodeType> value) {
        this.keyNodeType = value;
    }

    public java.util.Set<KeyNodeType> getKeyNodeType() {
        return keyNodeType;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
