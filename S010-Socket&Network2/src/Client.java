import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;
    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("192.168.1.34", 207);
        Scanner input = new Scanner(System.in);
        System.out.println("enter username");
        String username = "";
        boolean usernameValidation = false;
        try {
            do {
                username = input.nextLine();
                client.dos.writeUTF(username);
                String serverValidation = client.dis.readUTF();
                System.out.println(serverValidation);
                if (!serverValidation.startsWith("error")) {
                    usernameValidation = true;
                }
            } while (!usernameValidation);

            Thread messageListener = new Thread(new MessageListener(client.dis));
            messageListener.start();
            while (true) {
                System.out.println("Enter receiver username");
                String receiver = input.nextLine();
                System.out.println("Enter message");
                String message = input.nextLine();
                client.dos.writeUTF(receiver);
                client.dos.writeUTF(message);
            }
        } catch (Exception e) {
            client.socket.close();
            client.dis.close();
            client.dos.close();
        }
    }
}
class MessageListener implements Runnable {
    DataInputStream dis;
    MessageListener(DataInputStream dis) {
        this.dis = dis;
    }
    @Override
    public void run() {
        try {
            while (true) {
                String serverMsg = this.dis.readUTF();
                System.out.println(serverMsg);
            }
        } catch (Exception e) {
            try {
                dis.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
