import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by panikun on 22.11.15.
 */
public class RequestSender implements Runnable {
    public static final int PORT=11111;
    //Socket socket;
    float r;
    float x;
    float y;
    Ponto point;

    RequestSender(Ponto point,double radius){
        this.point=point;
        this.r = (float)radius;

    }

    @Override
    public void run() {
        this.y = (float)point.Y;
        this.x = (float)point.X;
        PrintStream ps;
        InputStreamReader ir;
        String message;
        try(
                Socket socket = new Socket("localhost", RequestSender.PORT);
                PrintStream pr= new PrintStream(socket.getOutputStream());
                BufferedReader brdr=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ) {
            pr.println(x + ";" + y + ";" + r);
            message=brdr.readLine();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            point.setGreyColor();
            return;
        } catch (IOException e) {
            point.setGreyColor();
            e.printStackTrace();
            return;
        }


//        try {
//            socket = new Socket("localhost", RequestSender.PORT);
//            ps = new PrintStream(socket.getOutputStream());
//        } catch (IOException e) {
//            System.err.println("Не удалось отправить запрос: ");
//            point.setGreyColor();
//            return;
//        }
       // pr.println(x + ";" + y + ";" + r);
       // ps.flush();



//        try {
//            ir = new InputStreamReader(socket.getInputStream());
//            BufferedReader br = new BufferedReader(ir);
//            message = br.readLine();
//        } catch (IOException e) {
//            point.setGreyColor();
//            System.err.println(e);
//            return;
//        }
        
        switch (message){
            case "0": {point.setRedColor();} break;
            case "1":{point.setBlueColor();} break;
            default:{point.setGreyColor();} break;
        }
        //closeSocket();
    }

//    private void closeSocket() {
//        try {
//            this.socket.close();
//        } catch (IOException e) {
//        }
//    }

}
