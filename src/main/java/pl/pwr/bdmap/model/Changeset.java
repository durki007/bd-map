package pl.pwr.bdmap.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "changeset")
public class Changeset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Timestamp created_at;
    private Long user_id;

    public Changeset() {
    }

    public Changeset(Long id, Timestamp created_at) {
        this.id = id;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Changeset changeset = (Changeset) o;
        return Objects.equals(id, changeset.id) && Objects.equals(created_at, changeset.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created_at);
    }

    @Override
    public String toString() {
        return "Changeset{" +
                "id=" + id +
                ", created_at=" + created_at +
                '}';
    }
}
