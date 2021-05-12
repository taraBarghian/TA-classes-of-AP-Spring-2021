import java.io.Serializable;

public class User implements Serializable {
    transient String username;
    String email;
    transient int password;
    public User(String username, String email, int password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
