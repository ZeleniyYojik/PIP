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

        try {
            ir= new InputStreamReader(client.getInputStream());
            BufferedReader br = new BufferedReader(ir);
            ps = new PrintStream(client.getOutputStream());
            message = br.readLine();
        } catch (IOException e) {
            return;
        }
        float[] params = new float[3];//x,y,r
        String[] par = message.split(";");
        if (par.length!=3){
            System.err.println("Неверный формат сообщения");
            return;
        }
        for (int i=0; i<3; i++){
            try {
                params[i] = Float.parseFloat(par[i]);
            }
            catch (NumberFormatException e){
                System.err.println("Неверный формат сообщения");
                return;
            }
        }


        ps.print(new Kontur(params[2]).isInKontur(new Point(params[0], params[1])));
        ps.flush();
        ps.close();
        System.out.println("Сообщение отправлено:   "+new Kontur(params[2]).isInKontur(new Point(params[0], params[1])));

    }

}
