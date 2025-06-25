package in.seth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket socket;

    BufferedReader br;
    PrintWriter out;


    public Client(){
        try{
            System.out.println("sending request to server...");
socket = new Socket("127.0.0.1",77);
            System.out.println("connection done.");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();




        } catch (Exception e) {

        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader started..");
            while (true) {
                try {
                    String msg =  br.readLine();
                    if (msg.equals("exist")) {
                        System.out.println("server terminated the chat");
                        break;
                    }

                    System.out.println("server : " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };

        new Thread(r1).start();


    }
    public void startWriting () {
        Runnable r2 = () -> {
            System.out.println("writer started..");
            while (true) {
                try {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }


        };

        new Thread(r2).start();

    }
    public static void main(String[] args) {
        System.out.println("this is client... ");
        new Client();
    }
}
