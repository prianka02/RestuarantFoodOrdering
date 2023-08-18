package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class managerServer {

    public static void main(String[] args) throws IOException{
	try {
        System.out.println("Manager Server is waiting for manager client.");
        ServerSocket serverSocket = new ServerSocket(6700);
        Socket sc = serverSocket.accept();

        System.out.println("Connection established with client!");

        OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
        BufferedWriter sWriter = new BufferedWriter(o);

        InputStreamReader isr = new InputStreamReader(sc.getInputStream());
        BufferedReader sReader = new BufferedReader(isr);

        File file = new File("E:\\Edu\\projectServer\\src\\com\\company\\managerPassFile");
        Scanner scanner = new Scanner(file);
        HashMap<String, String> loginInfo = new HashMap<>();

        while (true){
            String op = sReader.readLine();
            System.out.println("server is able to read this");

            if(op.equals("check")){

                String st1 = sReader.readLine();
                String st2 = sReader.readLine();
                HashMap<String, String> userdata = new HashMap<>();
                userdata.put(st1,st2);
                while (scanner.hasNext()){
                    String[] usernameAndPassword = scanner.nextLine().split(",");
                    loginInfo.put(usernameAndPassword[0],usernameAndPassword[1]);
                }
                if(loginInfo.equals(userdata)){
                    sWriter.write(1);
                    sWriter.flush();
                    System.out.println("successfully login!"+"\n");
                }
                else{
                    sWriter.write(-1);
                    System.out.println("failed!");

                    sWriter.flush();
              }
            }




        }


    }catch (IOException e){
	    e.printStackTrace();
    }
    }
}
