package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Session implements Runnable{

    //세선 매니저
    private SessionManager sessionManager;

    private final Socket socket;

    private final DataInputStream input;
    private final DataOutputStream output;


    public Session(Socket socket,SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        sessionManager.add(this);
    }

    @Override
    public void run() {
       while(true){
           try{
               String receive = input.readUTF();
               String[] parts = receive.split(" ",2);

               String command = parts[0];
               String mseeage =  parts[1];

               log("client -> server : "+command);
               switch (command){
                   case("/join"):       break;
                   case("/message"):    break;
                   case("/change"):     break;
                   case("/users"):      break;
                   case("/exit"):       break;
               }

               //
               output.writeUTF(receive);


           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
