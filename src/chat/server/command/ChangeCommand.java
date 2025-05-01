package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;

public class ChangeCommand implements Command{

    private final SessionManager sessionManager;

    public ChangeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String setname = args[1];
        String befoename = session.getUsername();
        session.setUsername(setname);
        sessionManager.sendAll("["+befoename+"] 님이"+"["+setname+"] 으로 이름을 변경하였습니다");
    }
}
