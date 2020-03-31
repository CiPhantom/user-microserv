package usermicroservice.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;
    private String fullname;
    private String username;
    private String role;
    private String mail;
    private String password;

    public User() {}

    public User(String fullname, String username, String role, String mail, String password) {
        this.fullname = fullname;
        this.username = username;
        this.role = role;
        this.mail = mail;
        this.password = password;
    }
}