import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketExample {

    private static ServerSocket serverSocket = null;

    public static void main(String[] args){
//        ServerSocket serverSocket = new ServerSocket(50001);
//        System.out.println(serverSocket.getLocalPort());
//
//        ServerSocket serverSocket = new ServerSocket();
//        serverSocket.bind(new InetSocketAddress(50001));
//
//        Socket socket = serverSocket.accept();
//
//        InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
//        String clinetIp = isa.getHostName();
//        String portNo = isa.getPort() + "";
//
//        System.out.println(clinetIp + ":" + portNo);
//
//        serverSocket.close();
        startServer();

    }

    public static void startServer() {
        Thread thread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(50001);
                System.out.println("Server started");

                while (true) {
                    Socket socket = serverSocket.accept();

                    InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                    System.out.println("Client connected: " + isa.getHostName() + ":" + isa.getPort());

                    while (true){
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        String msg = dis.readUTF();
                        System.out.println(msg);
                        if(msg.equals("end")){
                            break;
                        }
                    }

                    socket.close();

                    stopServer();

                    System.out.println("Client disconnected: " + isa.getHostName() + ":" + isa.getPort());
                }
            } catch (IOException e) {
                System.out.println("Client disconnected: " + e.getMessage());
            }
        });
        thread.start();
    }

    public static void stopServer() {
        try {
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException ignored) {
        }
    }
}
