import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       // FileWriter file = new FileWriter("1.txt");
        FileInputStream fis = new FileInputStream("1.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        System.out.println("name: " + user.username + " password: "+ user.password + " email: "+ user.email);
    }
}
