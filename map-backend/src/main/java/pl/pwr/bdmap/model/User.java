package pl.pwr.bdmap.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
    public User() {
        // Empty constructor
    }

    @Column(name = "id", nullable = false, unique = true, length = 10)
    @Id
    @GeneratedValue(generator = "USER_ID_GENERATOR", strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "role", nullable = false, length = 255)
    private String role;

    @OneToMany(mappedBy = "user", targetEntity = Changeset.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<Changeset> changeset = new java.util.HashSet<>();

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String value) {
        this.role = value;
    }

    public String getRole() {
        return role;
    }

    public void setChangeset(java.util.Set<Changeset> value) {
        this.changeset = value;
    }

    public java.util.Set<Changeset> getChangeset() {
        return changeset;
    }


    public String toString() {
        return String.valueOf(getId());
    }

}
