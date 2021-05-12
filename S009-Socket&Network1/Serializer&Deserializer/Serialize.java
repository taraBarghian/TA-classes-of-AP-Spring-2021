import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialize {
    public static void main(String[] args) throws IOException {
        User user = new User("saba", "saba@gmail.com", 1234);
        FileOutputStream fos = new FileOutputStream("1.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.close();
        fos.close();
    }
}
