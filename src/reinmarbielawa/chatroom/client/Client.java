package reinmarbielawa.chatroom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class Client implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private BufferedReader userIn;
    private PrintWriter userOut;
    private BufferedReader serverIn;
    private PrintWriter serverOut;
    private Thread incommingThread;

    public Client(BufferedReader userIn, PrintWriter userOut, BufferedReader serverIn, PrintWriter serverOut) {
        this.userIn = userIn;
        this.userOut = userOut;
        this.serverIn = serverIn;
        this.serverOut = serverOut;

        incommingThread = new Thread(() -> {
            String line;
            try {
                while ((line = this.serverIn.readLine()) != null)
                    this.userOut.println(line);
            } catch (IOException e) {
                LOGGER.warning(e.toString());
            }
        });
    }

    public void run() {
        LOGGER.info("Chatroom client has started.");
        userOut.println("Chatroom client has started.");

        incommingThread.start();

        String line;
        try {
            while ((line = userIn.readLine()) != null) {
                if (line.equals("\\q")) break;
                this.serverOut.println(line);
            }
        } catch (IOException e) {
            LOGGER.warning(e.toString());
        }
    }
}
