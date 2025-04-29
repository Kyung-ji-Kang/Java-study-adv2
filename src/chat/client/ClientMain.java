package chat.client;

public class ClientMain {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",PORT);

    }
}
