import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    DataInputStream dis = null;
    DataOutputStream dos = null;
    Socket socket = null;
    public Client(String IPAddress, int port) throws IOException {
        socket = new Socket(IPAddress, port);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public void playGame() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean hasGuessedTheWord = false;
        while (!hasGuessedTheWord) {
            int answer = input.nextInt();
            dos.writeInt(answer);
            String serverAnswer = dis.readUTF();
            if (serverAnswer.startsWith("true")){
                hasGuessedTheWord = true;
                System.out.println("You answered correctly");
            } else {
                System.out.println("try again");
            }
        }
        dis.close();
        dos.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 325);
        client.playGame();
    }
}
