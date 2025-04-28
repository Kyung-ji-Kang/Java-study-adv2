/*
package network.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientUser {

    Scanner sc = new Scanner(System.in);
    private User user;
    private Socket socket;
    DataInputStream input ;
    DataOutputStream output ;

    public ClientUser( Socket socket) throws IOException {
        this.socket = socket;
        this.user = new User();
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    //입장
    public void join(String[] parts) throws IOException {
        String command = parts[0];
        String name = parts[1];
        user.setName(name);
    }

    //메시지
    public void message(String[] parts,String message) throws IOException {
        String command = parts[0];
        output.writeUTF(user.getName()+" : " +message);
    }

    //이름 변경
    public void change(String[] parts, String name) throws IOException {
        String command = parts[0];
        String beforename = user.getName();
        user.setName(name);
        output.writeUTF(beforename+"님이 "+name+" 으로 이름을 변경하였습니다.");
    }

    //전체 사용자
    public void users(String[] parts){

    }


    public void exit() throws IOException {

    }
}
*/
