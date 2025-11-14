import java.io.IOException;
import java.net.*;


public class UdpServerExample {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(5000);

        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        datagramSocket.receive(receivePacket);

        byte[] bytes = receivePacket.getData();
        int num = receivePacket.getLength();

        String data = new String(bytes, 0, num);
        System.out.println(data);
        datagramSocket.close();
    }

}
