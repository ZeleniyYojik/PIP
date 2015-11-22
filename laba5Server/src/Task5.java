import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Task5 {
    public static final int PORT = 1111;
    //public static final int BUFF_SIZE = 1024;
    public static void main(String[] args) {
    int count=0;
        //new Server();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Task5.PORT);
        } catch (IOException e) {
            System.err.println("Не удалось открыть данный порт: " + Task5.PORT);
            return;
        }
        while (true) {
            try {
                System.out.println("Начинаю слушать на: "+serverSocket.toString());
                Socket client = serverSocket.accept();
                System.out.println("Принят клиент: "+client.toString()+"Номер: "+count);
                count++;
                new RequestHandler(client);
            } catch (IOException e) {
                System.err.println("Ошибка приема клиента");
            }

        }
    }
}