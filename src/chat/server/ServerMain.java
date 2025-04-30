package chat.server;

import java.io.IOException;

public class ServerMain {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        SessionManager sessionManager = new SessionManager();

        // CommandManager 점진적으로 변경 예정
        CommandManager commandManagerV1 = new CommandManagerV1(sessionManager);
        CommandManager commandManagerV2 = new CommandManagerV2(sessionManager);

        Server server = new Server(PORT,commandManagerV2,sessionManager);
        server.start();

    }
}
