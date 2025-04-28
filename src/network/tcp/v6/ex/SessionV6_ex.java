package network.tcp.v6.ex;

import network.tcp.v6.SessionManagerV6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV6_ex implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6_ex sessionmanager;
    private boolean closed = false;

    public SessionV6_ex(Socket socket, SessionManagerV6_ex sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionmanager = sessionManager;
        this.sessionmanager.add(this);
    }


    @Override
    public void run() {
        try{
            while(true){
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();
                log("client -> server: "+received);

                if(received.equals("exit")){
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received+"world!";
                output.writeUTF(toSend);
                log("client <- server: "+toSend);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            sessionmanager.remove(this);
            close();
        }

    }

    // 세션 종료시, 서버 종료시 동시에 호출될 수 있다.
    public synchronized void close(){
        if(closed){
            return;
        }
        closeAll(socket,input,output);
        closed = true;
        log("연결 종료: "+socket);
    }
}
