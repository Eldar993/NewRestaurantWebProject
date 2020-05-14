package com.example.restaurant.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "Roles")
public class UserRoles {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique=true , name = "Role_title")
    private String role_title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return id == userRoles.id &&
                Objects.equals(role_title, userRoles.role_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role_title);
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", role_title='" + role_title + '\'' +
                '}';
    }
}
