package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
            System.out.print("메시지를 입력하세요: ");
            String message = sc.nextLine();

            output.writeUTF(message);
            if(message.equals("exit")){
                break;
            }

            String receive = input.readUTF();
            System.out.println(receive);
        }


        input.close();
        output.close();
        socket.close();



    }
}
