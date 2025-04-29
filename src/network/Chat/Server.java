package network.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {

    private static final int PORT = 12345;
    public static  int USER_COUNT = 0;
    private static SessionManager sessionmanager = new SessionManager();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);

        log("서버 소캣 오픈 완료"+serverSocket);

        while(true){
            //클라이언트 접속시 서버 소켓을 통해 허용하는 코드
            Socket socket = serverSocket.accept();
            USER_COUNT++;
            log("소캣 연결"+ socket+"\n현재 유저수: "+USER_COUNT +"명");

            //
            Session session = new Session(socket,sessionmanager);
            Thread thread = new Thread(session);
            thread.start();


        }

    }


}
