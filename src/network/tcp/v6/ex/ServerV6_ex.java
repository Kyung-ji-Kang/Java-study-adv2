package network.tcp.v6.ex;

import network.tcp.v6.ServerV6;
import network.tcp.v6.SessionManagerV6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6_ex {

    private static final int PORT = 12345;

    public void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6_ex sessionManager = new SessionManagerV6_ex();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: "+ PORT);

        // Shutdown 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook,"shutdown"));


        try{
            while(true){
                Socket socket = serverSocket.accept();
                log("소켓 연결: " + socket);

                SessionV6_ex session = new SessionV6_ex(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: "+e);
        }
    }

    public static class ShutdownHook implements Runnable{

        private final ServerSocket serverSocket;
        private final SessionManagerV6_ex sessionManagerV6Ex;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6_ex sessionManagerV6Ex) {
            this.serverSocket = serverSocket;
            this.sessionManagerV6Ex = sessionManagerV6Ex;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");
            try{
                sessionManagerV6Ex.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("e = "+e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


