import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter '-' to exit\n");

        try (Socket socket = new Socket("localhost", 3000);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {

            while (true) {
                System.out.print("Client : ");
                String message = input.nextLine();

                if (message.equals("-")) {
                    break;
                }

                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();

                String message2 = dataInputStream.readUTF();
                System.out.println("Server : " + message2);

                if (message2.equals("-")){
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}