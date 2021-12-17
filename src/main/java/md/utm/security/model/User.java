package md.utm.security.model;

import lombok.Data;

@Data
public class User {

    private String name;
    private String email;
    private boolean confirmed;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.confirmed = false;
    }

    public void confirm() {
        this.confirmed = true;
    }
}
