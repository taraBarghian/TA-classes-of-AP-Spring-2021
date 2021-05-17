import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String username;
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        try {
            this.username = dis.readUTF();
            while (Server.users.containsKey(username)) {
                this.dos.writeUTF("error: username already exists");
                this.username = dis.readUTF();
            }
            this.dos.writeUTF("successfully registered");
            Server.users.put(username, this);
            Server.printMap();
            while (true) {
                String receiverUsername = this.dis.readUTF();
                String userMsg = this.dis.readUTF();
                if (Server.users.containsKey(receiverUsername)) {
                    if (receiverUsername.equals("everyone")) {
                        for (String user : Server.users.keySet()) {
                            ClientHandler receiverHandler = Server.users.get(user);
                            receiverHandler.dos.writeUTF(username + " : " + userMsg);
                        }
                    } else {
                        ClientHandler receiverHandler = Server.users.get(receiverUsername);
                        receiverHandler.dos.writeUTF(username + " : " + userMsg);
                    }
                }
            }
        } catch (Exception e) {
            Server.users.remove(username);
            try {
                this.socket.close();
                this.dis.close();
                this.dos.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
