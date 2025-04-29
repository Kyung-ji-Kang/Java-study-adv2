package network.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session){
        sessions.add(session);
    }

    public synchronized void remove(Session session){
        sessions.remove(session);
    }

    public synchronized void listAll() throws IOException {
        for(Session session:sessions){
            session.SendMessage(session.get_name());
        }
    }

    public synchronized void broadCast(String message) throws IOException {
        for(Session session:sessions){
            session.SendMessage(message);
        }
    }



}
