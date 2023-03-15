package ru.kata.spring.boot_security.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {


    public final static Role ROLE_ADMIN =  new Role("ROLE_ADMIN");
    public final static Role ROLE_USER =  new Role("ROLE_USER");
    public final static Role ROLE_UNKNOWN =  new Role("ROLE_UNKNOWN");


    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Id
    @Column(name = "role_id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String role;



    @Override
    public String getAuthority() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int roleId) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}
