import java.io.*;
import java.net.Socket;

/**
 * Created by panikun on 22.11.15.
 */
public class RequestHandler extends Thread {
    Socket client;
    RequestHandler(Socket client) {

        this.client=client;
        this.start();
    }

    @Override
    public void run() {
        InputStreamReader ir;
        PrintStream ps;
        String message;
        //System.out.println("Начинаю обработку");
        try {
            ir= new InputStreamReader(client.getInputStream());
            BufferedReader br = new BufferedReader(ir);
            ps = new PrintStream(client.getOutputStream());
            message = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        float[] params = new float[3];//x,y,r
        params[0]=1;
        params[1]=1;
        params[2]=2;

        ps.print(new Kontur(params[2]).isInKontur(new Point(params[0],params[1])));
        //System.out.println("Сообщение: "+message);
        try {
            client.close();
           // System.out.println("Клиент закрыт");
        } catch (IOException e) {
            System.err.println("Не удалось закрыть клиент");
            return;
        }
    }
}
