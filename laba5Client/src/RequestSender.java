import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by panikun on 22.11.15.
 */
public class RequestSender implements Runnable {
    public static final int PORT=11111;
    Socket socket;
    float r;
    float x;
    float y;
    Ponto point;

    RequestSender(Ponto point,double radius){
        this.point=point;
        this.r = (float)radius;

    }

   private void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        this.y = (float)point.Y;
        this.x = (float)point.X;
        PrintStream ps;
        InputStreamReader ir;
        try {
            socket = new Socket("localhost", RequestSender.PORT);
            ps = new PrintStream(socket.getOutputStream());
            ir = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Не удалось отправить запрос: ");
            point.setGreyColor();
            return;
        }
        ps.print(x + ";" + y + ";" + r);

        ps.close();
        BufferedReader br = new BufferedReader(ir);
        String message;
        try {
            message = br.readLine();
        } catch (IOException e) {
            point.setGreyColor();
            System.err.println("Why?");
            return;
        }
        System.out.print(message);
        switch (message){
            case "0": {point.setRedColor();} break;
            case "1":{point.setBlueColor();} break;
            default:{point.setGreyColor();} break;
        }
        closeSocket();
    }
}
