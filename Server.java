package in.seth;

import java.io.*;
import java.net.*;

public class Server {

    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;



    public Server(){
        try {
            server = new ServerSocket(77);
            System.out.println("server is ready to accept connection");
            System.out.println("waiting..");
           socket = server.accept();

           br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

           out = new PrintWriter(socket.getOutputStream());

           startReading();
           startWriting();

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader started..");
            while (true) {
                try {
                String msg =  br.readLine();
                if (msg.equals("exist")) {
                        System.out.println("client terminated the chat");
                        break;
                    }

                    System.out.println("client : " + msg);
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
            public static void main (String[]args){
                System.out.println("this is server..going to start server");
                new Server();

            }
        }
