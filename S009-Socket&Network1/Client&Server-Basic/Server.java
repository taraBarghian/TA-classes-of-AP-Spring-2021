import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    DataInputStream dis = null;
    DataOutputStream dos = null;
    Socket socket = null;
    ServerSocket ss = null;
    public Server (int port) throws IOException {
        ss = new ServerSocket(port);
        socket = ss.accept();
        System.out.println("client connected!");
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public void startGame() throws IOException {
        Random random = new Random();
        int num = random.nextInt(11);
        boolean hasGuessedTheWord = false;
        while (!hasGuessedTheWord) {
            int guess = dis.readInt();
            if (guess == num) {
                dos.writeUTF("true!");
                hasGuessedTheWord = true;
            } else {
                dos.writeUTF("false!");
            }
        }
        dos.close();
        dis.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(325);
        server.startGame();
    }
}
