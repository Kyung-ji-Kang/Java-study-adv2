package network.tcp.v6.ex;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6_ex {

    private List<SessionV6_ex> sessions = new ArrayList<>();

    public synchronized  void add(SessionV6_ex session){
        sessions.add(session);
    }

    public synchronized  void remove(SessionV6_ex session){
        sessions.remove(session);
    }

    public synchronized  void closeAll(){
        for(SessionV6_ex session : sessions){
            session.close();
        }
        sessions.clear();
    }
}
