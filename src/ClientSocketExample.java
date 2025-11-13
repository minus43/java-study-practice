import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketExample {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 50001);
        Scanner scanner = new Scanner(System.in);
        while(true) {

            String line = scanner.nextLine();
            System.out.println(line);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(line);
            out.flush();

            if(line.equals("end")) {
                scanner.close();
                break;
            }
        }
        socket.close();

    }
}
