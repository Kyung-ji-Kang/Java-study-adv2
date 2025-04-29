package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ServerCommand {
    Scanner sc = new Scanner(System.in);

    private User user;
    private Socket socket;
    private DataInputStream input ;
    private DataOutputStream output ;
    Session session;
    SessionManager sessionManager;

    public ServerCommand( Socket socket, Session session,DataInputStream input, DataOutputStream output,SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.user = new User();
        this.input = input;
        this.output = output;
        this.session = session;
        this.sessionManager = sessionManager;
        sessionManager.add(session);

    }

    public void parsing(String receive) throws IOException {
        String[] parts = receive.split(" ",2);
        String command = parts[0];
        String message =  parts[1];
        if(!(command.equals("user")||command.equals("exit"))){

        }

        switch (command){
            case("/join"):    join(message);       break;
            case("/message"): message(message);    break;
            case("/change"):  change(message);     break;
            case("/users"):     users();           break;
            case("/exit"):                         break;

            default:
                System.out.println("명령어를 다시 입력해주세요"); break;
        }
    }


    //입장
    public void join(String message) throws IOException {
        String name = message;
        user.setName(name);
        log(name+"님 입장 완료");
        output.writeUTF(name+"님이 입장하였습니다");
    }

    //메시지
    public void message(String message) throws IOException {
        sessionManager.broadCast(message);
    }

    //이름 변경
    public void change(String message) throws IOException {
        String beforename = user.getName();
        user.setName(message);
        log(beforename+"님이 "+ user.getName()+" 으로 이름을 변경하였습니다.");
        output.writeUTF(beforename+"님이 "+ user.getName()+" 으로 이름을 변경하였습니다.");
    }

    //전체 사용자
    public void users() throws IOException {
        sessionManager.listAll();
    }


    public void exit() throws IOException {

    }

    public String get_name(){
        return  user.getName();
    }
}
