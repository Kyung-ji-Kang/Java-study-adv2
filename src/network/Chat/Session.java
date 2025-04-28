package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class Session implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    public Session(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {


       while(true){
           try{
               String receive = input.readUTF();
               log("client -> server : "+receive);

               //exit 로직 작성
               if(receive.equals("exit")){
                   break;
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
