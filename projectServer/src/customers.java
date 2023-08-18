import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class customers implements Runnable{
      String userName;
    BufferedWriter sWriter;
    BufferedReader sReader;
     final static ArrayList<customers> customer= new ArrayList<>();

    public customers(Socket sc) {
        try {

            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            sWriter = new BufferedWriter(o);

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            sReader = new BufferedReader(isr);

            userName = sReader.readLine();
            customer.add(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                userName = sReader.readLine();
                System.out.println(userName);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
