import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class RequestSender implements Runnable {
    public static final int PORT = 11111;
    //Socket socket;
    float r;
    float x;
    float y;
    Ponto point;

    RequestSender(Ponto point, double radius) {
        this.point = point;
        this.r = (float) radius;
    }

    @Override
    public void run() {
        this.y = (float) point.Y;
        this.x = (float) point.X;
        Socket sock =null;
        String message;
        try (
                Socket socket = new Socket("localhost", RequestSender.PORT);
                PrintStream pr = new PrintStream(socket.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            pr.println(x + ";" + y + ";" + r);
            message = br.readLine();
        } catch (UnknownHostException e) {
            point.setGreyColor();
            return;
        } catch (IOException e) {
            return;
        }
        switch (message) {
            case "0": {
                point.setRedColor();
                if (point.isInKontur) {
                    point.needAnimation = true;
                } else {
                    point.needAnimation = false;
                }
                point.isInKontur = false;
            }
            break;
            case "1": {
                point.setBlueColor();
                point.isInKontur = true;
                point.needAnimation = false;
            }
            break;
            default: {
                point.setGreyColor();
            }
            break;
        }
    }

}
