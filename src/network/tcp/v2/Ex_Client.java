package network.tcp.v2;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class Ex_Client {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        log("클라이언트 시작");

        Socket socket = new Socket("localhost",PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        log("소캣 연결: "+socket);

        while(true){
            // 서버에게 문자 보내기
            System.out.print("메세지를 입력하세요 ('x' 종료): ");
            String toSend = bf.readLine();
            if(toSend.equals("x"))
            {
                output.writeUTF(toSend);
                System.out.println("시스템을 종료하겠습니다");
                Thread.sleep(2500);
                break;
            }
            output.writeUTF(toSend);
            log("cline -> server:"+toSend);

            // 서버로부터 문자 받기
            String received = input.readUTF();
            log("client <- server: "+received);
        }


        // 자원 정리
        log("연결 종료: "+socket);
        input.close();
        output.close();
        socket.close();
    }

}
