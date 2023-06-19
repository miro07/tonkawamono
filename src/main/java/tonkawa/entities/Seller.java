package entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.io.Serializable;

@Entity
@Table(name = "USERS",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Seller extends User implements Serializable {
    public Seller(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Seller() {
    }
}
