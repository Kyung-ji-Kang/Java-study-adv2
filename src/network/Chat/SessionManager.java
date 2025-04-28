package network.Chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static List<Session> sessions = new ArrayList<>();

    public void add(Session session){
        sessions.add(session);
    }

    public void remove(Session session){
        sessions.remove(session);
    }

    public void listAll(){
        for(Session session:sessions){

        }
    }



}
