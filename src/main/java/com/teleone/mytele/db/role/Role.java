package com.teleone.mytele.db.role;

import com.teleone.mytele.db.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role", schema = "myapp")
public class Role {

    private String[] roles = new String[] { "ROLE_USER", "ROLE_MOD", "ROLE_ADMIN" };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
