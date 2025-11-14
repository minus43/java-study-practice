import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    private static ServerSocket serverSocket = null;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        startServer();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String key = scanner.nextLine();
            if(key.toLowerCase().equals("q")) {
                break;
            }
        }
        scanner.close();

        stopServer();
    }

    private static void startServer() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    serverSocket = new ServerSocket(50001);
                    System.out.println("Server started");

                    while(true){
                        Socket socket = serverSocket.accept();

                        executorService.execute(()->{
                            try {
                                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                                System.out.println(isa.getAddress().getHostName()+":"+isa.getPort());

                                DataInputStream dis = new DataInputStream(socket.getInputStream());
                                String message = dis.readUTF();
                                System.out.println(message+" from "+socket.getRemoteSocketAddress());

                                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                                dos.writeUTF(message);
                                dos.flush();
                                System.out.println(message+" sent");

                                socket.close();
                                System.out.println(isa.getAddress().getHostName()+":"+isa.getPort()+"disconnected");
                            } catch (IOException e) {}
                        });
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        thread.start();
    }

    public static void stopServer(){
        try {
            serverSocket.close();
            executorService.shutdown();
            System.out.println("Server stopped");
        } catch (IOException e) {}
    }
}
