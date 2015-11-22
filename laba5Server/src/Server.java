import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 11111;
    //public static final int BUFF_SIZE = 1024;
    public static void main(String[] args) {
        //new Server();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Server.PORT);
        } catch (IOException e) {
            System.err.println("Не удалось открыть данный порт: " + Server.PORT);
            return;
        }
        System.out.println("Сервер запущен");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new RequestHandler(client);

            } catch (IOException e) {
                System.err.println("Ошибка приема клиента");
            }

        }
    }
}