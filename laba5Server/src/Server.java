import java.io.IOException;
import java.net.*;

/**
 * Created by panikun on 22.11.15.
 */
public class Server extends Thread {
    Server(){
        start();
    }

    @Override
    public void run() {
        System.out.println("Слушает: " + this.toString());
        ServerSocket  serverSocket;
        try {
            serverSocket =new ServerSocket(Task5.PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true){
            try {
                Socket client = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Ошибка приема клиента");
            }


        }

    }

    String acceptClient() throws IOException{

       // Socket client =  socket.accept();
       // System.out.println("Принят клиент: " + client.toString());
        //client.getOutputStream();
        return " ";
    }
}
