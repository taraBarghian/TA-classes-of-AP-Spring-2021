import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    static Map<String, ClientHandler> users;
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(207);
        users = new ConcurrentHashMap<>();
        try {
            while (true) {
                Socket socket = ss.accept();
                Thread clientThread = new Thread(new ClientHandler(socket));
                clientThread.start();
            }
        } catch (Exception e) {
            ss.close();
        }
    }
    public static  void printMap() {
        int count = 1;
        for (String user: users.keySet()) {
            System.out.println(count + " - " + user);
            count++;
        }
        System.out.println();
    }
}
