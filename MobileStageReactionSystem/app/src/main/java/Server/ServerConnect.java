package Server;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by sinne on 20-9-2017.
 */


public class ServerConnect extends AsyncTask<String, String, String>  {
//    implements
//    InteractActivity.OnListener

    private String dstAddress;
    private int dstPort;
    private PrintWriter out1;


    private static TextView textResponse;
    private EditText editTextAddress, editTextPort;
    private Button buttonConnect;
    private String message = "";
    private String received = "";
    private static String kq = "";

    DataOutputStream dos;

    private boolean running = false;

    private Socket socket = null;
    private String jsonString;


    public ServerConnect(String addr, int port) {
        dstAddress = addr;
        dstPort = port;
    }

    @Override
    protected void onProgressUpdate(String... values) {

        super.onProgressUpdate(values);

    }

    @Override
    protected String doInBackground(String... params) {


        try {



            socket = new Socket(dstAddress, dstPort);

            out1 = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in1 = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            dos = new DataOutputStream(socket.getOutputStream());
            do {
                try {
                    if (!in1.ready()) {
//past code here that notifies user/log that connection is succesful
                        running = true;
                        if (message != null) {
//                            InteractActivity.handler.obtainMessage(0, 0, -1,
//                                    "Server: " + message).sendToTarget();
                            received = message;
                            message = "";

                        }
                    }
                    int num = in1.read();
                    message += Character.toString((char) num);

                } catch (Exception classNot) {
                }


            } while (!socket.isClosed());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void sendJSON(String json) {
        while (!running) {}


        jsonString = json +"\r\n";

        Thread sendThread = new Thread(new Runnable() {
            public void run() {
                out1.print(jsonString);
                out1.flush();
            }
        });
        sendThread.start();

        while (sendThread.isAlive()) {}
    }

    public boolean isConnected() {
        if (socket == null) return false;
        return socket.isConnected();
    }

    public String disconnect() {
        try {
            socket.close();
        } catch (Exception ioException) {
            return ioException.toString();
        }

        return "ok";
    }


//    @Override
//    public void listener(String text) {
//
//    }
}

