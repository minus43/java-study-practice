import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpClientExample {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        String data = "Hello World";
        byte[] bytes = data.getBytes("UTF-8");
        DatagramPacket sendPacket = new DatagramPacket(
                bytes, bytes.length, new InetSocketAddress("localhost",5000)
        );
        datagramSocket.send(sendPacket);
        datagramSocket.close();
    }
}
