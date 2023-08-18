import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class customersServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Customer Server is waiting for customers.");
        ServerSocket serverSocket = new ServerSocket(7500);
        while (true){
            Socket sc = serverSocket.accept();
            System.out.println("Connection established with client!");

            customers cs= new customers(sc);
            Thread t = new Thread(cs);
            t.start();
        }
    }
}
