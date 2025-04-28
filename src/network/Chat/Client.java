package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import static util.MyLogger.log;

public class Client {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {


        log("클라이언트 시작");
        Socket socket = new Socket("127.0.0.1",PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.print("명령어를 입력하시오");
            String message = sc.nextLine();

            output.writeUTF(message);
            if(isExit(message)){
                break;
            }
            /*
            String[] parts = message.split(" ",2);
            String command = parts[0];
            String value = parts[1];

            switch (command){
                case("/join"): clientUser.join(parts); break;
                case("/message"): clientUser.message(parts); break;
                case("/change"): clientUser.change(parts); break;
                case("/users"):
                //case("/exit"): clientUser.exit(); break;
                default:{
                    System.out.println("다시 입력해주시기 바랍니다");
                }

            }*/
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
