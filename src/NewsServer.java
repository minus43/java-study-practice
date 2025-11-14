import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsServer {
    private static DatagramSocket datagramSocket = null;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        startServer();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String key = scanner.nextLine();
            if(key.toLowerCase().equals("q")) {
                break;
            }
        }
        scanner.close();

        stopServer();
    }

    public static void startServer() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    datagramSocket = new DatagramSocket(50001);
                    System.out.println("Server started");

                    while(true) {
                        DatagramPacket recievePacket = new DatagramPacket(new byte[1024], 1024);
                        datagramSocket.receive(recievePacket);

                        executorService.execute(() -> {
                            try{
                                String newsKind = new String(
                                        recievePacket.getData(), 0, recievePacket.getLength(), "UTF-8"
                                );

                                SocketAddress socketAddress = recievePacket.getSocketAddress();

                                for(int i=0; i<=10; i++){
                                    String data = newsKind + ": 뉴스" + i;
                                    byte[] bytes = data.getBytes("UTF-8");
                                    DatagramPacket sendPacket = new DatagramPacket(
                                            bytes, 0, bytes.length, socketAddress
                                    );
                                    datagramSocket.send(sendPacket);
                                }
                            } catch (Exception e) {}
                        });
                    }
                } catch (Exception e) {}
            }
        };
        thread.start();
    }

    public static void stopServer() {
        datagramSocket.close();
        executorService.shutdown();
        System.out.println("Server stopped");
    }
}
