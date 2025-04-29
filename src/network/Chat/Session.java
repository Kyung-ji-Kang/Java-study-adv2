package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Session implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final ServerCommand servercommand;
    private final SessionManager sessionManager;

    public Session(Socket socket, SessionManager sessionManager ) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.servercommand = new ServerCommand(socket,this,this.input,this.output,sessionManager);

    }

    @Override
    public void run() {
       while(true){
           try{
               String receive = input.readUTF();
               log("client -> server : "+receive);
               servercommand.parsing(receive); //파싱
//               output.writeUTF(receive);


           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        /*try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }

    public String get_name(){
        return servercommand.get_name();
    }

    public void SendMessage(String message) throws IOException {
        output.writeUTF(message);
    }


}
