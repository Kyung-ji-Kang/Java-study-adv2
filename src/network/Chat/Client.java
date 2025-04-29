package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static util.MyLogger.log;

public class Client {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {


        log("클라이언트 시작");
        Socket socket = new Socket("127.0.0.1",PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);


        //메세지를 받는 리스터 스레드
        Thread listener  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        String in = input.readUTF();
                        System.out.println();
                        log(in);
                        System.out.print("명령어를 입력하시오: ");

                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        listener.start();

        while(true){
            String message = sc.nextLine();
            output.writeUTF(message);
            System.out.println();
            if(isExit(message)){
                break;
            }

        }

        input.close();
        output.close();
        socket.close();
    }

    private static boolean isExit(String message){
        String[] parts = message.split(" ",2);
        if(parts[0].equals("exit")){
            return true;
        }
        return false;
    }


}
