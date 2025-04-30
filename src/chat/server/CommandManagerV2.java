package chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager{

    private static final String DELEMITER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


    @Override
    public void execute(String totalMessage, Session session) throws IOException {


        if(totalMessage.startsWith("/join")){
            // /join|{username}
            String[] split = totalMessage.split(DELEMITER);
            String username = split[1];
            session.setUsername(username);
            sessionManager.sendAll(username +"님이 입장했습니다.");
        } else if (totalMessage.startsWith("/message")) {
            // 클라이언트에 전체 메세지 보내기
            String[] split = totalMessage.split(DELEMITER);
            String message = split[1];
            String username = session.getUsername();
            sessionManager.sendAll("["+username+"] : "+message);
        } else if (totalMessage.startsWith("/change")){
            // /change|han2
            String[] split = totalMessage.split(DELEMITER);
            String setname = split[1];
            String befoename = session.getUsername();
            session.setUsername(setname);
            sessionManager.sendAll("["+befoename+"] 님이"+"["+setname+"] 으로 이름을 변경하였습니다");

        } else if (totalMessage.startsWith("/users")){
            List<String> usernames = sessionManager.getAllUsername();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자: ").append(usernames.size()).append("\n");
            for(String username : usernames){
                sb.append(" - ").append(username).append("\n");
            }
            session.send(sb.toString());
        } else if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        } else{
            session.send("처리할 수 없는 명렁어 입니다: "+ totalMessage);
        }

        if(totalMessage.startsWith("/exit")){
            throw new IOException("exit");
        }


    }
}
