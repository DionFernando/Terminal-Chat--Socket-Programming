import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter '-' to exit\n");

        try (ServerSocket serverSocket = new ServerSocket(3000);
             Socket socket = serverSocket.accept();
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                System.out.print("Server : ");
                String message = input.nextLine();

                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();

                if (message.equals("-")){
                    break;
                }

                try {
                    String msg = dataInputStream.readUTF();
                    System.out.println("Client : " + msg);

                    if (msg.equals("-")){
                        break;
                    }
                } catch (EOFException e) {
                    System.out.println("Client disconnected");
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}