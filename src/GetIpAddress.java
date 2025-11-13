import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class GetIpAddress {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress ia1 = InetAddress.getLocalHost();
        System.out.println(Arrays.toString(ia1.getAddress()));
        System.out.println(ia1.getHostAddress());
        System.out.println(ia1.getCanonicalHostName());
        System.out.println(ia1.getHostName());

        System.out.println("\n\n");
        InetAddress ia2 = InetAddress.getByName("localhost");
        System.out.println(Arrays.toString(ia2.getAddress()));
        System.out.println(ia2.getHostAddress());
        System.out.println(ia2.getCanonicalHostName());
        System.out.println(ia2.getHostName());

        System.out.println("\n\n");
        InetAddress[] isArr = InetAddress.getAllByName("localhost");
        System.out.println(Arrays.toString(isArr));

        System.out.println("\n\n");
        String ip = InetAddress.getLoopbackAddress().getHostAddress();
        System.out.println(ip);


    }
}
