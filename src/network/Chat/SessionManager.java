package network.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionManager {

    private static List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session){
        sessions.add(session);
    }

    public synchronized void remove(Session session){
        sessions.remove(session);
    }

    public synchronized void UserAll() throws IOException {
        ArrayList<String> users = new ArrayList<>();

        for(Session session:sessions){
            String name = session.get_name();
            users.add(name);
        }
        String  userlist = users.toString();
        broadCast(userlist);
    }

    public synchronized void broadCast(String message) throws IOException {
        for(Session session:sessions){
            session.SendMessage(message);
        }
    }



}
